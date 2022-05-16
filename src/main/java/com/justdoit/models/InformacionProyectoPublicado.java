/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.models;

/**
 *
 * @author Andres
 */
public class InformacionProyectoPublicado {
    
    private String id;
    private String titulo;
    private long valorObjetivo;

    public InformacionProyectoPublicado() {
    }

    public InformacionProyectoPublicado(String id, String titulo, long valorObjetivo) {
        this.id = id;
        this.titulo = titulo;
        this.valorObjetivo = valorObjetivo;
    }

    public long getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(long valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
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
    
    
    
}
