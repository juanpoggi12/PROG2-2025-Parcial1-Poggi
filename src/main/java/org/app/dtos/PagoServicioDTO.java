package org.app.dtos;

import jakarta.persistence.*;
import org.app.models.ContratoServicio;
import org.app.models.PagoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagoServicioDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ContratoServicioDTO contratoServicio;
    private LocalDate fechaPago;
    private BigDecimal monto;

    public PagoServicioDTO(){}

    public PagoServicioDTO(Integer id, ContratoServicioDTO contratoServicio, LocalDate fechaPago, BigDecimal monto) {
        this.id = id;
        this.contratoServicio = contratoServicio;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContratoServicioDTO getContratoServicio() {
        return contratoServicio;
    }

    public void setContratoServicio(ContratoServicioDTO contratoServicio) {
        this.contratoServicio = contratoServicio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    public static PagoServicioDTO fromEntity(PagoServicio pago){
        if(pago == null){
            return null;
        }
        PagoServicioDTO dto = new PagoServicioDTO();
        dto.setId(pago.getId());
        dto.setMonto(pago.getMonto());
        dto.setFechaPago(pago.getFechaPago());
        dto.setContratoServicio(ContratoServicioDTO.fromEntity(pago.getContratoServicio()));
        return dto;
    }
}
