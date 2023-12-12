package com.web.service;

import com.dao.ClienteDAO;
import com.dao.EmpleadoDAO;
import com.dao.MovimientoDAO;
import com.dao.PersonaDAO;
import com.dto.ResponseDTO;
import com.entities.Cliente;
import com.entities.Empleado;
import com.entities.Movimientos;
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
    
    @EJB
    private MovimientoDAO movimientoDAO;
    
    @EJB
    private ClienteDAO clienteDAO;
    
    @EJB
    private EmpleadoDAO empleadoDAO;

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
    public ResponseDTO getClientByCode(
            @QueryParam("codigoCliente") String codigoCliente,
            @QueryParam("codigoEmpleado") String codigoEmpleado
    ) throws Exception  {
        //Validar la longitud del codigo enviado
        if(codigoCliente!=null)
        {
            if(codigoCliente.length() !=9)
            {
                 ResponseDTO responseException=new ResponseDTO();
              
                 String mensaje="Codigo de cliente NO tiene la longitud correcta";
                 responseException.setMessage(mensaje);
                 return responseException;
                //throw new Exception();
            } 
        }
      
        if(codigoEmpleado!=null)
        {
            if(codigoEmpleado.length() !=9)
            {
                ResponseDTO responseException=new ResponseDTO();
              
                 String mensaje="Codigo de empleado NO tiene la longitud correcta";
                 responseException.setMessage(mensaje);
                 return responseException;
                //throw new Exception("");
            }
        }

        if(codigoCliente== null && codigoEmpleado==null)
        {
             ResponseDTO responseException=new ResponseDTO();
             String mensaje="Los 2 codigos no pueden ser vacios";
             responseException.setMessage(mensaje);
             return responseException;
            //throw new Exception("Los 2 codigos no pueden ser vacios");
        }
        
        ResponseDTO response=new ResponseDTO();
        
        //Busca si es codigo de cliente en tabla de clientes
        Cliente clienteSeleccionado=null;
        if(codigoCliente!=null)
        {
            clienteSeleccionado=clienteDAO.obtenerClientePorCodigo(codigoCliente);
            
            if(clienteSeleccionado==null)
            {
             ResponseDTO responseException=new ResponseDTO();
             String mensaje="El cliente con codigo "+codigoCliente+ " no fue encontrado";
             responseException.setMessage(mensaje);
             return responseException;
            }
            Empleado empleado= empleadoDAO.obtenerEmpleadoPorIdPersona(clienteSeleccionado.getIdPersona());
            if(empleado!=null)
            {
                 response.setCargo(empleado.getCargo());
            }
        }else{
            
            //Buscar si es codigo de empleado en tabla de empleados
            Empleado empleado= empleadoDAO.obtenerEmpleado(codigoEmpleado);
            response.setCargo(empleado.getCargo());
            
            //Buscar cliente
            clienteSeleccionado=clienteDAO.obtenerClientePorIdPersona(empleado.getEmpleadoPK().getIdPersona());
        }
        
        if(clienteSeleccionado==null)
        {
         ResponseDTO responseException=new ResponseDTO();
         String mensaje="Cliente NO encontrado";
         responseException.setMessage(mensaje);
         return responseException;
        }

        //Con idPerson buscar en tabla de personas 
        Persona persona= personaDAO.obtenerDatosPersonaPorId(clienteSeleccionado.getIdPersona());
        response.setNombre(persona.getNombre());
        response.setApellido(persona.getApellido());
        response.setEdad(persona.getEdad());
        response.setIdPersona(persona.getIdPersona());
        response.setNumeroCuenta(clienteSeleccionado.getNumeroCuenta());
        response.setFechaCreacionCuenta(clienteSeleccionado.getFechaCreacionCuenta());
        
        //Buscar movimientos
        List<Movimientos> listaMovimientos=movimientoDAO.obtenerMovimientosPorIdCliente(clienteSeleccionado.getIdCliente());
        response.setMovimientos(listaMovimientos);
        
        //Calcula la suma y resta de movimientos
        response.setSaldoActual(movimientoDAO.calcularSaldoActual(listaMovimientos));
        
        return response;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
