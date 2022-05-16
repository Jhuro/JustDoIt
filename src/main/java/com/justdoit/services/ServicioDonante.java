/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.services;

import com.justdoit.PersistenceManager;
import com.justdoit.dto.DonanteDTO;
import com.justdoit.entity.Donante;
import com.justdoit.entity.Proyecto;
import com.justdoit.models.DonacionProyecto;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Andres
 */
@Path("/donante")
@Produces(MediaType.APPLICATION_JSON)
public class ServicioDonante {

    @PersistenceContext(unitName = "mongoPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEmprendedor(DonanteDTO donanteDTO) {
        Donante donante = new Donante(donanteDTO.getNombre(), donanteDTO.getCorreoElectronico(), donanteDTO.getCedula(), donanteDTO.getTipoDeProyecto());
        JSONObject rta = new JSONObject();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(donante);
            entityManager.getTransaction().commit();
            entityManager.refresh(donante);
            rta.put("donante_id", donante.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            donante = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }

    @POST
    @Path("/actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEmprendedor(DonanteDTO donanteDTO) {
        Query q = entityManager.createQuery("SELECT d FROM Donante d WHERE d.cedula = " + donanteDTO.getCedula());
        Donante donante;
        try {
            donante = (Donante) q.getSingleResult();
            donante.setNombre(donanteDTO.getNombre());
            donante.setCorreoElectronico(donanteDTO.getCorreoElectronico());
            donante.setTipoDeProyecto(donanteDTO.getTipoDeProyecto());
        } catch (Exception e) {
            JSONObject rta = new JSONObject();
            rta.put("Informacion", "Cedula incorrecta");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(donante);
            entityManager.getTransaction().commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            donante = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(donante).build();
    }

    @GET
    @Path("/getDonantes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query q = entityManager.createQuery("SELECT d FROM Donante d ORDER BY d.nombre ASC");
        List<Donante> donantes = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(donantes).build();
    }

    @GET
    @Path("{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmprendedor(@PathParam("cedula") String cedula) {
        Query q = entityManager.createQuery("SELECT d FROM Donante d WHERE d.cedula = " + cedula);
        Donante donante;
        try {
            donante = (Donante) q.getSingleResult();
        } catch (Exception e) {
            JSONObject rta = new JSONObject();
            rta.put("informacion", "No hay ningun usuario con esa cedula");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(donante).build();
    }

    @POST
    @Path("/donacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarDonacion(DonacionProyecto donacionProyecto) {
        Query q = entityManager.createQuery("SELECT d FROM Donante d WHERE d.id = '" + donacionProyecto.getIdDonante() + "'");
        Donante donante;
        Proyecto proyecto;
        JSONObject rta = new JSONObject();
        try {
            donante = (Donante) q.getSingleResult();
            q = entityManager.createQuery("SELECT p FROM Proyecto p WHERE p.id = '" + donacionProyecto.getIdProyecto() + "'");
            proyecto = (Proyecto) q.getSingleResult();
            proyecto.setValorActual(proyecto.getValorActual() + donacionProyecto.getValor());
        } catch (Exception e) {
            rta.put("Informacion", "Donante o proyecto no encontrado");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }
        
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyecto);
            entityManager.getTransaction().commit();
            rta.put("proyecto_id", proyecto.getId());
            rta.put("valor_donado", donacionProyecto.getValor());
            rta.put("Informacion", "Donacion realizada correctamente, ¡Gracias por apoyar el proyecto!");
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            donante = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }
}
