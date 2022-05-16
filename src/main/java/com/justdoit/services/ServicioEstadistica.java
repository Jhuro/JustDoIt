/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.justdoit.services;

import com.justdoit.PersistenceManager;
import com.justdoit.entity.Proyecto;
import com.justdoit.models.InformacionProyectoPublicado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 *
 * @author Andres
 */
@Path("/estadisticas")
@Produces(MediaType.APPLICATION_JSON)
public class ServicioEstadistica {

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
    @Path("/valorFinanciadoPromedio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValorFinanciadoPromedio() {
        Query q = entityManager.createQuery("SELECT p.valorActual FROM Proyecto p");
        List<Long> valoresActualesProyectos = q.getResultList();
        double valorFinanciadoPromedio = 0;
        JSONObject rta = new JSONObject();
        for (int i = 0; i < valoresActualesProyectos.size(); i++) {
            valorFinanciadoPromedio += valoresActualesProyectos.get(i);
        }
        valorFinanciadoPromedio /= valoresActualesProyectos.size();

        rta.put("Valor_Financiado_Promedio", valorFinanciadoPromedio);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(rta.toJSONString()).build();
    }
    
    @GET
    @Path("/proyectosPublicados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Query q = entityManager.createQuery("SELECT p FROM Proyecto p ORDER BY p.titulo ASC");
        List<Proyecto> proyectos = q.getResultList();
        InformacionProyectoPublicado informacion;
        List<InformacionProyectoPublicado> infoProyectos = new ArrayList<InformacionProyectoPublicado>();
        for (int i = 0; i < proyectos.size(); i++) {
            informacion = new InformacionProyectoPublicado();
            informacion.setId(proyectos.get(i).getId());
            informacion.setTitulo(proyectos.get(i).getTitulo());
            informacion.setValorObjetivo(proyectos.get(i).getValorObjetivo());
            infoProyectos.add(informacion);
        }
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(infoProyectos).build();
    }
}
