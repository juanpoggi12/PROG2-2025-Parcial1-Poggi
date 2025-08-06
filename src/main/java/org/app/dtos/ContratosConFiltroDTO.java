package org.app.dtos;

import org.app.models.Enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratosConFiltroDTO {
    private String nombreCliente;
    private TipoServicio tipoServicio;
    private LocalDate fechaInicioDesde;
    private LocalDate fechaInicioHasta;
    private BigDecimal tarifaMensualDesde;
    private BigDecimal tarifaMensualHasta;

    public ContratosConFiltroDTO(){
    }

    public ContratosConFiltroDTO(String nombreCliente, TipoServicio tipoServicio, LocalDate fechaInicioDesde, LocalDate fechaInicioHasta, BigDecimal tarifaMensualDesde, BigDecimal tarifaMensualHasta) {
        this.nombreCliente = nombreCliente;
        this.tipoServicio = tipoServicio;
        this.fechaInicioDesde = fechaInicioDesde;
        this.fechaInicioHasta = fechaInicioHasta;
        this.tarifaMensualDesde = tarifaMensualDesde;
        this.tarifaMensualHasta = tarifaMensualHasta;
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

    public LocalDate getFechaInicioDesde() {
        return fechaInicioDesde;
    }

    public void setFechaInicioDesde(LocalDate fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }

    public LocalDate getFechaInicioHasta() {
        return fechaInicioHasta;
    }

    public void setFechaInicioHasta(LocalDate fechaInicioHasta) {
        this.fechaInicioHasta = fechaInicioHasta;
    }

    public BigDecimal getTarifaMensualDesde() {
        return tarifaMensualDesde;
    }

    public void setTarifaMensualDesde(BigDecimal tarifaMensualDesde) {
        this.tarifaMensualDesde = tarifaMensualDesde;
    }

    public BigDecimal getTarifaMensualHasta() {
        return tarifaMensualHasta;
    }

    public void setTarifaMensualHasta(BigDecimal tarifaMensualHasta) {
        this.tarifaMensualHasta = tarifaMensualHasta;
    }
}
