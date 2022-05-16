/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.services;

import com.justdoit.PersistenceManager;
import com.justdoit.dto.EmprendedorDTO;
import com.justdoit.entity.Emprendedor;
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
@Path("/emprendedor")
@Produces(MediaType.APPLICATION_JSON)
public class ServicioEmprendedor {

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
    public Response registrarEmprendedor(EmprendedorDTO emprendedorDTO) {
        Emprendedor emprendedor = new Emprendedor(emprendedorDTO.getNombre(), emprendedorDTO.getCorreoElectronico(), emprendedorDTO.getCedula());
        JSONObject rta = new JSONObject();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(emprendedor);
            entityManager.getTransaction().commit();
            entityManager.refresh(emprendedor);
            rta.put("emprendedor_id", emprendedor.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            emprendedor = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }

    @POST
    @Path("/actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEmprendedor(EmprendedorDTO emprendedorDTO) {
        Query q = entityManager.createQuery("SELECT e FROM Emprendedor e WHERE e.cedula = " + emprendedorDTO.getCedula());
        Emprendedor emprendedor;
        try {
            emprendedor = (Emprendedor) q.getSingleResult();
            emprendedor.setNombre(emprendedorDTO.getNombre());
            emprendedor.setCorreoElectronico(emprendedorDTO.getCorreoElectronico());
        } catch (Exception e) {
            JSONObject rta = new JSONObject();
            rta.put("Informacion", "Cedula incorrecta");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(emprendedor);
            entityManager.getTransaction().commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            emprendedor = null;
        } finally {
            entityManager.clear();
            entityManager.close();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(emprendedor).build();
    }

    @GET
    @Path("/getEmprendedores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query q = entityManager.createQuery("SELECT e FROM Emprendedor e order by e.nombre ASC");
        List<Emprendedor> emprendedores = q.getResultList();
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(emprendedores).build();
    }

    @GET
    @Path("{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmprendedor(@PathParam("cedula") String cedula) {
        Query q = entityManager.createQuery("SELECT e FROM Emprendedor e WHERE e.cedula = " + cedula);
        Emprendedor emprendedor;
        try {
            emprendedor = (Emprendedor) q.getSingleResult();
        } catch (Exception e) {
            JSONObject rta = new JSONObject();
            rta.put("informacion", "No hay ningun usuario con esa cedula");
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(emprendedor).build();

    }

}
