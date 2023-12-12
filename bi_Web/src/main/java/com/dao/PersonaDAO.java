package com.dao;

import com.entities.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author davidmac
 */
@Stateless
public class PersonaDAO extends GenericDAO<Persona>{ 
    
    
    public PersonaDAO(){
        super(Persona.class);
    }
     
    
    public Persona obtenerDatosPersonaPorId(Integer idPerson)
    {
        String sql
                = " SELECT p "
                + " FROM Persona p "
                + " WHERE p.idPersona = :idPersona";

        Query query = this.em.createQuery(sql);
        query.setParameter("idPersona", idPerson);
        List<Persona> resultList = query.getResultList();
        if(resultList.isEmpty())
        {
            return null;
        }else{
            return resultList.get(0);
        }
    }
     
    
    
}
