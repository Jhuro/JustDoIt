/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.dto;

/**
 *
 * @author jupbc
 */

public class EmprendedorDTO{
    
    private String nombre;
    private String correoElectronico;
    private long cedula;

    public EmprendedorDTO(){
    }
    
    public EmprendedorDTO(String nombre, String correoElectronico, long cedula){
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.cedula = cedula;
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
    
}
