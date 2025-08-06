package org.app.models;

import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity()
@Table(name = "pago_servicio")
public class PagoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "contrato_servicio", nullable = false)
    private ContratoServicio contratoServicio;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal monto;

    public PagoServicio(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContratoServicio getContratoServicio() {
        return contratoServicio;
    }

    public void setContratoServicio(ContratoServicio contratoServicio) {
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

    public PagoServicio(Integer id, ContratoServicio contratoServicio, LocalDate fechaPago, BigDecimal monto) {
        this.id = id;
        this.contratoServicio = contratoServicio;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }
}