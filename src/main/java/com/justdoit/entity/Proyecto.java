/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.entity;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author jupbc
 */
@NoSql(dataFormat = DataFormatType.MAPPED)
@Entity
@XmlRootElement
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;

    @NotNull
    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;

    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;
    
    private String titulo;
    private String descripcion;
    private String emprendedor_id;
    private long valorObjetivo;
    private long valorActual;
    private String fechaInicio;
    private String fechaFinal;
    private String tipoDeProyecto;
    private String estado;

    public Proyecto() {

    }

    public Proyecto(String titulo, String descripción, String emprendedor_id, long valorObjetivo, long valorActual, String fechaFinal, String tipoDeProyecto) throws ParseException {
        this.titulo = titulo;
        this.descripcion = descripción;
        this.emprendedor_id = emprendedor_id;
        this.valorObjetivo = valorObjetivo;
        this.valorActual = valorActual;
        this.fechaInicio = new SimpleDateFormat("YYYY-MM-dd").format(Calendar.getInstance().getTime());
        this.fechaFinal = fechaFinal;
        this.tipoDeProyecto = tipoDeProyecto;
        this.estado = "FINANCIAMIENTO";
    }
    
    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }

    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
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

    public String getEmprendedor_id() {
        return emprendedor_id;
    }

    public void setEmprendedor_id(String emprendedor_id) {
        this.emprendedor_id = emprendedor_id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
