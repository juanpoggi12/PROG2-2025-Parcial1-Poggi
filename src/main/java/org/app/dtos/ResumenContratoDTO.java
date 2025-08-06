package org.app.dtos;

import org.app.models.Enums.TipoServicio;

import java.math.BigDecimal;

public class ResumenContratoDTO {
    private TipoServicio tipoServicio;
    private int cantidadContratosCancelados;
    private BigDecimal montoTotalCobrado;

    public ResumenContratoDTO(){
    }

    public ResumenContratoDTO(TipoServicio tipoServicio, int cantidadContratosCancelados, BigDecimal montoTotalCobrado) {
        this.tipoServicio = tipoServicio;
        this.cantidadContratosCancelados = cantidadContratosCancelados;
        this.montoTotalCobrado = montoTotalCobrado;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getCantidadContratosCancelados() {
        return cantidadContratosCancelados;
    }

    public void setCantidadContratosCancelados(int cantidadContratosCancelados) {
        this.cantidadContratosCancelados = cantidadContratosCancelados;
    }

    public BigDecimal getMontoTotalCobrado() {
        return montoTotalCobrado;
    }

    public void setMontoTotalCobrado(BigDecimal montoTotalCobrado) {
        this.montoTotalCobrado = montoTotalCobrado;
    }
}
