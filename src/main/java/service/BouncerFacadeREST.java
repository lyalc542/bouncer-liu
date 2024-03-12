/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cst8218.chang.bouncer.liu.entity.Bouncer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author liuch
 */
@Stateless
@Path("cst8218.chang.bouncer.liu.bouncer")
public class BouncerFacadeREST extends AbstractFacade<Bouncer> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public BouncerFacadeREST() {
        super(Bouncer.class);
    }
    
    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createBouncer(Bouncer entity) {
        if (entity.getId() != null) {
            // Id is not null, it's a bad request.
            return Response.status(Response.Status.BAD_REQUEST).entity("Id must be null for creation").build();
        }
        
        super.create(entity);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @GET
    @Path("count")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response countREST() {
        return Response.ok(super.count()).build();
    }

    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateBouncer(@PathParam("id") Long id, Bouncer entity) {
        if (entity.getId() != null && !id.equals(entity.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Mismatched ID in request").build();
        }

        Bouncer existingBouncer = getEntityManager().find(Bouncer.class, id);
        if (existingBouncer == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Bouncer with ID " + id + " not found.").build();
        }

        existingBouncer.update(entity);
        super.edit(existingBouncer);
        return Response.ok(existingBouncer).build();
    }
    
    
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bouncer entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Bouncer entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bouncer find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bouncer> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }



    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
