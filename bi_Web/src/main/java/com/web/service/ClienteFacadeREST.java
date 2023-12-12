package com.web.service;

import com.dao.PersonaDAO;
import com.entities.Cliente;
import com.entities.Persona;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
    @EJB
    private PersonaDAO personaDAO;

    public ClienteFacadeREST() {
        super(Cliente.class);
    }

    @GET
    @Path("getClients")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<Cliente> getClients() {
        return super.findAll();
    }
    
    @GET
    @Path("getClientByCode")
    @Produces({MediaType.APPLICATION_JSON})
    public Persona getClientByCode(
            @QueryParam("codigoCliente") String codeInstance,
            @QueryParam("codigoEmpleado") String codeCompany
    )  {
        //Validar la longitud del codigo enviado
        
        //Busca si es codigo de cliente en tabla de clientes
        
        //Buscara si es codigo de empleado en tabla de empleados
        
        //Con idPerson buscar en tabla de personas 
        Persona persona= personaDAO.obtenerDatosPersonaPorId(1);
        
        //Buscar movimientos
        
        
        return persona;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
