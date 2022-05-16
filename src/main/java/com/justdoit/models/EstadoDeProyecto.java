/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.models;

/**
 *
 * @author jupbc
 */
public class EstadoDeProyecto {

    private String idProyecto;
    private String idEmprendedor;
    private String estado;

    public EstadoDeProyecto(){
        
    }
    
    public EstadoDeProyecto(String idProyecto, String idEmprendedor, String estado) {
        this.idProyecto = idProyecto;
        this.idEmprendedor = idEmprendedor;
        this.estado = estado;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(String idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
