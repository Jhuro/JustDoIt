/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.models;

/**
 *
 * @author Andres
 */
public class ActualizacionProyecto {
    
    private String id;
    private String titulo;
    private String descripcion;
    private String tipoDeProyecto;

    public ActualizacionProyecto() {
    }

    public ActualizacionProyecto(String id, String titulo, String descripcion, String tipoDeProyecto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoDeProyecto = tipoDeProyecto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTipoDeProyecto() {
        return tipoDeProyecto;
    }

    public void setTipoDeProyecto(String tipoDeProyecto) {
        this.tipoDeProyecto = tipoDeProyecto;
    }
    
    
    
}
