package org.app.dtos;

import jakarta.persistence.*;
import org.app.models.ContratoServicio;
import org.app.models.Enums.Estado;
import org.app.models.Enums.TipoServicio;
import org.app.models.PagoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoServicioDTO {
    private Integer id;
    private String nombreCliente;
    private TipoServicio tipoServicio;
    private BigDecimal tarifaMensual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Estado estado = Estado.ACTIVO;
    private List<PagoServicio> pagos = new ArrayList<>();

    public ContratoServicioDTO() {
    }

    public ContratoServicioDTO(Integer id, String nombreCliente, TipoServicio tipoServicio, BigDecimal tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin, Estado estado, List<PagoServicio> pagos) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.tarifaMensual = tarifaMensual;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.pagos = pagos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public BigDecimal getTarifaMensual() {
        return tarifaMensual;
    }

    public void setTarifaMensual(BigDecimal tarifaMensual) {
        this.tarifaMensual = tarifaMensual;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<PagoServicio> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoServicio> pagos) {
        this.pagos = pagos;
    }

    public static ContratoServicioDTO fromEntity(ContratoServicio contratoServicio){
        if(contratoServicio == null){
            return null;
        }
        ContratoServicioDTO dto = new ContratoServicioDTO();
        dto.setId(contratoServicio.getId());
        dto.setEstado(contratoServicio.getEstado());
        dto.setPagos(contratoServicio.getPagos());
        dto.setFechaFin(contratoServicio.getFechaFin());
        dto.setNombreCliente(contratoServicio.getNombreCliente());
        dto.setTarifaMensual(contratoServicio.getTarifaMensual());
        dto.setTipoServicio(contratoServicio.getTipoServicio());
        return dto;
    }
}
