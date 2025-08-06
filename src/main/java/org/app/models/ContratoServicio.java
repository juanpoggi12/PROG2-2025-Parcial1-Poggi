package org.app.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.app.models.Enums.Estado;
import org.app.models.Enums.TipoServicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrato_servicio")
public class ContratoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_cliente", length = 100, nullable = false)
    private String nombreCliente;

    @Column(name = "tipo_servicio", nullable = false)
    private TipoServicio tipoServicio;

    @Column(name = "tarifa_mensual", precision = 12, scale = 2, nullable = false)
    private BigDecimal tarifaMensual;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.ACTIVO;

    @OneToMany(mappedBy = "contratoServicio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagoServicio> pagos = new ArrayList<>();

    public ContratoServicio(){}

    public ContratoServicio(Integer id, String nombreCliente, TipoServicio tipoServicio, BigDecimal tarifaMensual, LocalDate fechaInicio, LocalDate fechaFin, Estado estado, List<PagoServicio> pagos) {
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
}
