package com.gamblia.model.criteria;

import com.gamblia.model.Movimiento;

import java.util.Date;

public class MovimientoCriteria extends Movimiento {

    private Date fechaDesde;
    private Date fechaHasta;

    public MovimientoCriteria() {
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
