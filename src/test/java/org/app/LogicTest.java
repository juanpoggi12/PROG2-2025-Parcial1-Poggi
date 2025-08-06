package org.app;

import org.app.dtos.*;
import org.app.models.ContratoServicio;
import org.app.models.Enums.Estado;
import org.app.models.Enums.TipoServicio;
import org.app.services.Logic;
import org.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogicTest {
    private Logic logic;
    private Session session;

    private ContratoServicio contrato1;

    @BeforeAll
    void setUp() {
        logic = Logic.getInstance();
        session = HibernateUtil.getSession();
        session.beginTransaction();

        contrato1 = new ContratoServicio();
        contrato1.setNombreCliente("juan");
        contrato1.setTipoServicio(TipoServicio.AGUA);
        contrato1.setTarifaMensual(new BigDecimal("100"));
        contrato1.setFechaInicio(LocalDate.of(2024, 1, 1));
        contrato1.setFechaFin(LocalDate.of(2024, 12, 31));
        contrato1.setEstado(Estado.ACTIVO);

        session.persist(contrato1);
        session.getTransaction().commit();
    }

    @AfterAll
    void tearDown() {
        if (session != null && session.isOpen()) {
            session.beginTransaction();
            session.createQuery("delete from ContratoServicio").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Test
    void testRegistrarPagoCompletoYActualizaEstado() {
        BigDecimal montoTotal = new BigDecimal("100").multiply(new BigDecimal("12"));
        RegistrarPagoDTO dto = new RegistrarPagoDTO(
                contrato1.getId(),
                montoTotal,
                null
        );

        RegistrarPagoDTO resultado = logic.registrarPagoYAcualizarEstadoContrato(dto);

        assertNotNull(resultado);
        assertEquals(Estado.CANCELADO, resultado.getEstado());
    }

    @Test
    void testRegistrarPagoYNoCancelaContrato() {
        BigDecimal monto = new BigDecimal("500");
        RegistrarPagoDTO dto = new RegistrarPagoDTO(
                contrato1.getId(),
                monto,
                null
        );
        RegistrarPagoDTO resultado = logic.registrarPagoYAcualizarEstadoContrato(dto);
        assertNotNull(resultado);
        assertEquals(Estado.ACTIVO, resultado.getEstado());
    }

    @Test
    void testBuscarContratosPorNombre() {
        ContratosConFiltroDTO dto = new ContratosConFiltroDTO();
        dto.setNombreCliente("juan");
        dto.setTipoServicio(null);
        dto.setFechaInicioDesde(null);
        dto.setFechaInicioHasta(null);

        List<ContratoServicioDTO> resultados = logic.obtenerContratosConFiltrosOpcionales(dto);

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("juan", resultados.get(0).getNombreCliente().toLowerCase());
    }

    @Test
    void testObtenerCreditosNoFinalizados() {
        RangoFechasDTO dto = new RangoFechasDTO();
        dto.setFechaInicioDesde(LocalDate.of(2024, 1, 1));
        dto.setFechaInicioHasta(LocalDate.of(2024, 12, 31));

        List<ResumenContratoDTO> resultados = logic.obtenerResumenContratosCancelados(dto);

        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
    }
}
