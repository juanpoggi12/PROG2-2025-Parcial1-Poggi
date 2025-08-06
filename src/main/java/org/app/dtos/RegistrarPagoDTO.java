package org.app.dtos;

import org.app.models.Enums.Estado;

import java.math.BigDecimal;

public class RegistrarPagoDTO {
    private Integer idContrato;
    private BigDecimal montoPago;
    private Estado estado;

    public RegistrarPagoDTO(){}

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public RegistrarPagoDTO(Integer idContrato, BigDecimal montoPago, Estado estado) {
        this.idContrato = idContrato;
        this.montoPago = montoPago;
        this.estado = estado;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }
}
