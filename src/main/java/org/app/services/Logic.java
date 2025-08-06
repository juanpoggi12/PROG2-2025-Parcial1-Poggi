package org.app.services;

import com.mysql.cj.conf.url.ReplicationDnsSrvConnectionUrl;
import jakarta.persistence.criteria.*;
import org.app.dtos.*;
import org.app.models.ContratoServicio;
import org.app.models.Enums.Estado;
import org.app.models.PagoServicio;
import org.app.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logic {
    public static Logic instance;
    public Logic(){}
    public static Logic getInstance() {
        if(instance == null){
            return new Logic();
        }
        return instance;
    }

    public RegistrarPagoDTO registrarPagoYAcualizarEstadoContrato(RegistrarPagoDTO dto){
        try(Session session = HibernateUtil.getSession()){
            if(dto.getIdContrato() == null || dto.getMontoPago() == null){
                throw new NullPointerException("faltan agregar datos");
            }
            ContratoServicio contrato = session.get(ContratoServicio.class, dto.getIdContrato());
            if (contrato == null) {
                throw new IllegalArgumentException("contrato solicitado no existente");
            }
            session.beginTransaction();

            PagoServicio pago = new PagoServicio();
            pago.setContratoServicio(contrato);
            pago.setMonto(dto.getMontoPago());
            pago.setFechaPago(LocalDate.now());

            session.persist(pago);

            int meses = (contrato.getFechaFin().getYear() - contrato.getFechaInicio().getYear()) * 12 + (contrato.getFechaFin().getMonthValue() - contrato.getFechaInicio().getMonthValue());
            BigDecimal totalEsperado = contrato.getTarifaMensual().multiply(BigDecimal.valueOf(meses));

            if (dto.getMontoPago().compareTo(totalEsperado) >= 0) {
                contrato.setEstado(Estado.CANCELADO);
            }else if(contrato.getFechaFin().isBefore(LocalDate.now())) {
                contrato.setEstado(Estado.VENCIDO);
            }else{
                contrato.setEstado(Estado.ACTIVO);
            }
            session.merge(contrato);
            session.getTransaction().commit();

            dto.setEstado(contrato.getEstado());
            return dto;
        }
    }

    public List<ContratoServicioDTO> obtenerContratosConFiltrosOpcionales(ContratosConFiltroDTO dto){
        try(Session session = HibernateUtil.getSession()){
            if (dto.getNombreCliente() == null) {
                throw new IllegalArgumentException("falta agregar nombre de cliente");
            }

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ContratoServicio> query = cb.createQuery(ContratoServicio.class);
            Root<ContratoServicio> contrato = query.from(ContratoServicio.class);

            List<Predicate> condiciones = new ArrayList<>();

            condiciones.add(cb.equal(contrato.get("nombreCliente"), dto.getNombreCliente()));

            if (dto.getTipoServicio() != null) {
                condiciones.add(cb.equal(contrato.get("tipoServicio"), dto.getTipoServicio()));
            }
            if (dto.getFechaInicioDesde() != null) {
                condiciones.add(cb.greaterThanOrEqualTo(contrato.get("fechaInicio"), dto.getFechaInicioDesde()));
            }
            if (dto.getFechaInicioHasta() != null) {
                condiciones.add(cb.lessThanOrEqualTo(contrato.get("fechaInicio"), dto.getFechaInicioHasta()));
            }
            if (dto.getTarifaMensualDesde() != null) {
                condiciones.add(cb.greaterThanOrEqualTo(contrato.get("tarifaMensual"), dto.getTarifaMensualDesde()));
            }
            if (dto.getTarifaMensualHasta() != null) {
                condiciones.add(cb.lessThanOrEqualTo(contrato.get("tarifaMensual"), dto.getTarifaMensualHasta()));
            }

            query.select(contrato)
                    .where(condiciones.toArray(new Predicate[0]))
                    .orderBy(cb.desc(contrato.get("fechaInicio")));

            List<ContratoServicio> contratos = session.createQuery(query).getResultList();

            return contratos.stream()
                    .map(ContratoServicioDTO::fromEntity)
                    .collect(Collectors.toList());
        }
    }

    public List<ResumenContratoDTO> obtenerResumenContratosCancelados(RangoFechasDTO dto){
        try(Session session = HibernateUtil.getSession()){
            if (dto.getFechaInicioDesde() == null || dto.getFechaInicioHasta() == null) {
                throw new IllegalArgumentException("falta agregar los rangos de fechas");
            }
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ResumenContratoDTO> cq = cb.createQuery(ResumenContratoDTO.class);
            Root<ContratoServicio> contrato = cq.from(ContratoServicio.class);

            Predicate filtroEstado = cb.equal(contrato.get("estado"), Estado.CANCELADO);
            Predicate filtroFecha = cb.between(contrato.get("fechaInicio"), dto.getFechaInicioDesde(), dto.getFechaInicioHasta());

            Expression<Integer> meses = cb.function(
                    "TIMESTAMPDIFF",
                    Integer.class,
                    cb.literal("MONTH"),
                    contrato.get("fechaInicio"),
                    contrato.get("fechaFin")
            );

            Expression<Double> montoPorContrato = cb.prod(contrato.get("tarifaMensual"), cb.toDouble(meses));

            cq.select(cb.construct(
                    ResumenContratoDTO.class,
                    contrato.get("tipoServicio"),
                    cb.count(contrato),
                    cb.sum(montoPorContrato)
            ));

            cq.where(cb.and(filtroEstado, filtroFecha));
            cq.groupBy(contrato.get("tipoServicio"));
            return session.createQuery(cq).getResultList();
        }
    }

    public List<ResumenContratoNoCanceladoDTO> obtenerResumenContratosNoCancelados() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ContratoServicio> cq = cb.createQuery(ContratoServicio.class);
            Root<ContratoServicio> contrato = cq.from(ContratoServicio.class);

            Predicate estadoActivo = cb.equal(contrato.get("estado"), Estado.ACTIVO);
            Predicate estadoVencido = cb.equal(contrato.get("estado"), Estado.VENCIDO);
            cq.where(cb.or(estadoActivo, estadoVencido));

            List<ContratoServicio> contratos = session.createQuery(cq).getResultList();

            List<ResumenContratoNoCanceladoDTO> resumen = contratos.stream().map(c -> {
                long mesesContratados = ChronoUnit.MONTHS.between(c.getFechaInicio(), c.getFechaFin()) + 1;
                BigDecimal montoTotalEsperado = c.getTarifaMensual().multiply(BigDecimal.valueOf(mesesContratados));

                BigDecimal montoPagado = c.getPagos().stream()
                        .map(p -> p.getMonto() != null ? p.getMonto() : BigDecimal.ZERO)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                return new ResumenContratoNoCanceladoDTO(c.getId(), c.getNombreCliente(), montoTotalEsperado, montoPagado);
            }).collect(Collectors.toList());
            return resumen;
        }
    }
}
