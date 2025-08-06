package org.app.dtos;

import java.math.BigDecimal;

public class ResumenContratoNoCanceladoDTO {
    private Integer idContrato;
    private String nombreCliente;
    private BigDecimal montoTotalEsperado;
    private BigDecimal montoPagado;

    public ResumenContratoNoCanceladoDTO(){}

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public BigDecimal getMontoTotalEsperado() {
        return montoTotalEsperado;
    }

    public void setMontoTotalEsperado(BigDecimal montoTotalEsperado) {
        this.montoTotalEsperado = montoTotalEsperado;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public ResumenContratoNoCanceladoDTO(Integer idContrato, String nombreCliente, BigDecimal montoTotalEsperado, BigDecimal montoPagado) {
        this.idContrato = idContrato;
        this.nombreCliente = nombreCliente;
        this.montoTotalEsperado = montoTotalEsperado;
        this.montoPagado = montoPagado;
    }
}
