/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jupbc
 */
public class ProyectoDTO {
    
    private String titulo;
    private String descripcion;
    private long cedulaEmprendedor;
    private long valorObjetivo;
    private long valorActual; 
    private String fechaInicio; 
    private String fechaFinal;
    private String tipoDeProyecto;

    public ProyectoDTO(){
        
    }
    
    public ProyectoDTO(String titulo, String descripción, long cedula, long valorObjetivo, String fechaFinal, String tipoDeProyecto) throws ParseException {
        this.titulo = titulo;
        this.descripcion = descripción;
        this.cedulaEmprendedor = cedula;
        this.valorObjetivo = valorObjetivo;
        this.valorActual = 0;
        this.fechaInicio = new SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime());;
        this.fechaFinal = fechaFinal;
        this.tipoDeProyecto = tipoDeProyecto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getCedulaEmprendedor() {
        return cedulaEmprendedor;
    }

    public void setCedulaEmprendedor(long cedulaEmprendedor) {
        this.cedulaEmprendedor = cedulaEmprendedor;
    }

    public long getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(long valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public long getValorActual() {
        return valorActual;
    }

    public void setValorActual(long valorActual) {
        this.valorActual = valorActual;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTipoDeProyecto() {
        return tipoDeProyecto;
    }

    public void setTipoDeProyecto(String tipoDeProyecto) {
        this.tipoDeProyecto = tipoDeProyecto;
    }
}
