package com.gamblia.model;

public class Mesa {
    private Integer id;
    private String nombre;
    private Integer max;
    private Integer idJuego;
    private String psswd;
    private Double apuestaMin;
    private Integer code;

    public Mesa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(Integer idJuego) {
        this.idJuego = idJuego;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public Double getApuestaMin() {
        return apuestaMin;
    }

    public void setApuestaMin(Double apuestaMin) {
        this.apuestaMin = apuestaMin;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
