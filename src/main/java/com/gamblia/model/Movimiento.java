package com.gamblia.model;

import java.util.Date;

public class Movimiento {
    private Integer id;
    private Integer idCarteraOrigen;
    private Integer idCatertaDestino;
    private String asunto;
    private Double cantidad;
    private Integer idOperacion;
    private Date fecha;

    public Movimiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCarteraOrigen() {
        return idCarteraOrigen;
    }

    public void setIdCarteraOrigen(Integer idCarteraOrigen) {
        this.idCarteraOrigen = idCarteraOrigen;
    }

    public Integer getIdCatertaDestino() {
        return idCatertaDestino;
    }

    public void setIdCatertaDestino(Integer idCatertaDestino) {
        this.idCatertaDestino = idCatertaDestino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
