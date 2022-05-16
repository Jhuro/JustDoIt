/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jupbc
 */
public class DonacionProyecto {

    private String idProyecto;
    private String idDonante;
    private String fecha;
    private long valor;

    public DonacionProyecto(){
        
    }
    
    public DonacionProyecto(String idProyecto, String idDonante, long valor) {
        this.idProyecto = idProyecto;
        this.idDonante = idDonante;
        this.fecha = new SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime());
        this.valor = valor;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdDonante() {
        return idDonante;
    }

    public void setIdDonante(String idDonante) {
        this.idDonante = idDonante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }
    
    
}
