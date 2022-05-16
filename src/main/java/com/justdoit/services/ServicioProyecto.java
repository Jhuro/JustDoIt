/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.services;

import com.justdoit.PersistenceManager;
import com.justdoit.dto.ProyectoDTO;
import com.justdoit.entity.Proyecto;
import com.justdoit.models.ActualizacionProyecto;
import com.justdoit.models.EstadoDeProyecto;
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
@Path("/proyecto")
@Produces(MediaType.APPLICATION_JSON)
public class ServicioProyecto {

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

    @GET
    @Path("/getproyectos/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("cedula") String cedula) {
        Query q = entityManager.createQuery("SELECT e.id FROM Emprendedor e WHERE e.cedula = " + cedula);
        String emprendedor_id;
        JSONObject rta = new JSONObject();
        try {
            emprendedor_id = q.getSingleResult().toString();
            rta.put("Informacion", "Emprendedor no encontrado");
        } catch (Exception e) {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }
        
        q = entityManager.createQuery("SELECT p FROM Proyecto p WHERE p.emprendedor_id = '" + emprendedor_id + "'");
        List<Proyecto> proyectos = q.getResultList();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(proyectos).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearProyecto(ProyectoDTO proyectoDTO) {
        Query q = entityManager.createQuery("SELECT e.id FROM Emprendedor e WHERE e.cedula = " + proyectoDTO.getCedulaEmprendedor());
        JSONObject rta = new JSONObject();
        Proyecto proyecto = null;
        try {
            String emprendedor_id = q.getSingleResult().toString();

            //Se crea el proyecto
            proyecto = new Proyecto(proyectoDTO.getTitulo(), proyectoDTO.getDescripcion(),
                    emprendedor_id, proyectoDTO.getValorObjetivo(), proyectoDTO.getValorActual(),
                    proyectoDTO.getFechaFinal(), proyectoDTO.getTipoDeProyecto());
        } catch (Exception e) {

            rta.put("Informacion", "Emprendedor no encontrado");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(proyecto);
            entityManager.getTransaction().commit();
            entityManager.refresh(proyecto);
            rta.put("proyecto_id", proyecto.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            proyecto = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }

    @POST
    @Path("/actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProyecto(ActualizacionProyecto actualizacionProyecto) {
        Query q = entityManager.createQuery("SELECT p FROM Proyecto p WHERE p.id = " + actualizacionProyecto.getId());
        JSONObject rta = new JSONObject();
        Proyecto proyecto = null;
        try {
            proyecto = (Proyecto) q.getSingleResult();
            proyecto.setTitulo(actualizacionProyecto.getTitulo());
            proyecto.setDescripcion(actualizacionProyecto.getDescripcion());
            proyecto.setTipoDeProyecto(actualizacionProyecto.getTipoDeProyecto());

        } catch (Exception e) {
            rta.put("Informacion", "Proyecto no encontrado");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyecto);
            entityManager.getTransaction().commit();
            rta.put("proyecto_id", proyecto.getId());
            rta.put("titulo", proyecto.getTitulo());
            rta.put("descripcion", proyecto.getDescripcion());
            rta.put("tipoDeProyecto", proyecto.getTipoDeProyecto());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            proyecto = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(proyecto).build();
    }

    @POST
    @Path("/estado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cambiarEstado(EstadoDeProyecto estadoDeProyecto) {
        Query q = entityManager.createQuery("SELECT p FROM Proyecto p WHERE p.id = '" + estadoDeProyecto.getIdProyecto() + "'");
        JSONObject rta = new JSONObject();
        Proyecto proyecto = null;
        try {
            proyecto = (Proyecto) q.getSingleResult();
            proyecto.setEstado(estadoDeProyecto.getEstado());
        } catch (Exception e) {
            rta.put("Informacion:", "Proyecto no encontrado");
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(proyecto);
            entityManager.getTransaction().commit();
            rta.put("proyecto_id", proyecto.getId());
            rta.put("estado", proyecto.getEstado());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            proyecto = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }
}
