package com.dao;

import com.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author davidmac
 */
@Stateless
public class ClienteDAO extends GenericDAO<Cliente>{ 
    
      public ClienteDAO(){
        super(Cliente.class);
    }
      
      public Cliente obtenerClientePorCodigo(String idCliente)
      {
           String sql
                = " SELECT c "
                + " FROM Cliente c "
                + " WHERE c.idCliente = :idCliente";

        Query query = this.em.createQuery(sql);
        query.setParameter("idCliente", idCliente);
        List<Cliente> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return null;
        }else{
            return resultList.get(0);
        }
      }
      
      public Cliente obtenerClientePorIdPersona(int idPersona)
      {
          String sql
                = " SELECT c "
                + " FROM Cliente c "
                + " WHERE c.idPersona = :idPersona";

        Query query = this.em.createQuery(sql);
        query.setParameter("idPersona", idPersona);
        List<Cliente> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return null;
        }else{
            return resultList.get(0);
        }
      }
}
