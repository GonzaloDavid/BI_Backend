package com.web.service;

import com.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author davidmac
 */
@Stateless
@Path("com.entities.cliente")
public class ClienteFacadeREST extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @GET
    @Path("getClients")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Cliente> getClients() {
        return super.findAll();
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
