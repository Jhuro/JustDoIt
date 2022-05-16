/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.dto;


/**
 *
 * @author jupbc
 */
public class DonanteDTO {
    
    private String nombre;
    private String correoElectronico;
    private long cedula;
    private String tipoDeProyecto;

    public DonanteDTO(){
        
    }
    
    public DonanteDTO(String nombre, String correoElectrónico, long cédula, String tipoDeProyecto) {
        this.nombre = nombre;
        this.correoElectronico = correoElectrónico;
        this.cedula = cédula;
        this.tipoDeProyecto = tipoDeProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getTipoDeProyecto() {
        return tipoDeProyecto;
    }

    public void setTipoDeProyecto(String tipoDeProyecto) {
        this.tipoDeProyecto = tipoDeProyecto;
    }
    
}
