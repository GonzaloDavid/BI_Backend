package com.dao;

import com.entities.Empleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author davidmac
 */
@Stateless
public class EmpleadoDAO extends GenericDAO<Empleado>{ 
    
      public EmpleadoDAO(){
        super(Empleado.class);
    }
      
      
      public Empleado obtenerEmpleado(String idEmpleado)
      {
          String sql
                = " SELECT e "
                + " FROM Empleado e "
                + " WHERE e.empleadoPK.idEmpleado = :idEmpleado";

        Query query = this.em.createQuery(sql);
        query.setParameter("idEmpleado", idEmpleado);
        List<Empleado> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return null;
        }else{
            return resultList.get(0);
        }
      }
      
      public Empleado obtenerEmpleadoPorIdPersona(int idPersona)
      {
          String sql
                = " SELECT e "
                + " FROM Empleado e "
                + " WHERE e.empleadoPK.idPersona = :idPersona";

        Query query = this.em.createQuery(sql);
        query.setParameter("idPersona", idPersona);
        List<Empleado> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return null;
        }else{
            return resultList.get(0);
        }
      }
}
