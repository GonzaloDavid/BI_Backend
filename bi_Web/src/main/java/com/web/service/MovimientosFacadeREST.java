package com.web.service;

import com.entities.Movimientos;
import com.entities.MovimientosPK;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author davidmac
 */
@Stateless
@Path("com.entities.movimientos")
public class MovimientosFacadeREST extends AbstractFacade<Movimientos> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    private MovimientosPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idMovimiento=idMovimientoValue;idCliente=idClienteValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.entities.MovimientosPK key = new com.entities.MovimientosPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idMovimiento = map.get("idMovimiento");
        if (idMovimiento != null && !idMovimiento.isEmpty()) {
            key.setIdMovimiento(new java.lang.Integer(idMovimiento.get(0)));
        }
        java.util.List<String> idCliente = map.get("idCliente");
        if (idCliente != null && !idCliente.isEmpty()) {
            key.setIdCliente(idCliente.get(0));
        }
        return key;
    }

    public MovimientosFacadeREST() {
        super(Movimientos.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
