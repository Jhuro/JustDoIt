/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.entity;

/**
 *
 * @author jupbc
 */
public class ProyectoEmprendedor {
    
    private String idProyecto;
    private long idEmprendedor;

    public ProyectoEmprendedor(){
        
    }
    
    public ProyectoEmprendedor(String idProyecto, long idEmprendedor) {
        this.idProyecto = idProyecto;
        this.idEmprendedor = idEmprendedor;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public long getIdEmprendedor() {
        return idEmprendedor;
    }

    public void setIdEmprendedor(long idEmprendedor) {
        this.idEmprendedor = idEmprendedor;
    }
}
