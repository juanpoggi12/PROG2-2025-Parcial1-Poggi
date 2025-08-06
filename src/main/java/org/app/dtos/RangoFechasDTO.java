package org.app.dtos;

import org.hibernate.sql.results.spi.LoadContexts;

import java.time.LocalDate;

public class RangoFechasDTO {
    private LocalDate fechaInicioDesde;
    private LocalDate fechaInicioHasta;

    public RangoFechasDTO(){
    }

    public void setFechaInicioDesde(LocalDate fechaInicioDesde) {
        this.fechaInicioDesde = fechaInicioDesde;
    }

    public void setFechaInicioHasta(LocalDate fechaInicioHasta) {
        this.fechaInicioHasta = fechaInicioHasta;
    }

    public RangoFechasDTO(LocalDate fechaInicioDesde, LocalDate fechaInicioHasta) {
        this.fechaInicioDesde = fechaInicioDesde;
        this.fechaInicioHasta = fechaInicioHasta;
    }

    public LocalDate getFechaInicioDesde() {
        return fechaInicioDesde;
    }

    public LocalDate getFechaInicioHasta() {
        return fechaInicioHasta;
    }
}
