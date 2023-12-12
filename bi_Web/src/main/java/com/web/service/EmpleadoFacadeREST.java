package com.web.service;

import com.entities.Empleado;
import com.entities.EmpleadoPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author davidmac
 */
@Stateless
@Path("com.entities.empleado")
public class EmpleadoFacadeREST extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    private EmpleadoPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idEmpleado=idEmpleadoValue;idPersona=idPersonaValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.entities.EmpleadoPK key = new com.entities.EmpleadoPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idEmpleado = map.get("idEmpleado");
        if (idEmpleado != null && !idEmpleado.isEmpty()) {
            key.setIdEmpleado(idEmpleado.get(0));
        }
        java.util.List<String> idPersona = map.get("idPersona");
        if (idPersona != null && !idPersona.isEmpty()) {
            key.setIdPersona(idPersona.get(0));
        }
        return key;
    }

    public EmpleadoFacadeREST() {
        super(Empleado.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
