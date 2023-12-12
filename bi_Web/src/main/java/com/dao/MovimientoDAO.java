package com.dao;

import com.entities.Movimientos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author davidmac
 */
@Stateless
public class MovimientoDAO extends GenericDAO<Movimientos>{ 
    
     public MovimientoDAO(){
        super(Movimientos.class);
    }
     
    
     public List<Movimientos> obtenerMovimientosPorIdCliente(String codigoCliente)
     {
         String sql
                = " SELECT m "
                + " FROM Movimientos m "
                + " WHERE m.movimientosPK.idCliente = :idCliente";

         Query query = this.em.createQuery(sql);
        query.setParameter("idCliente", codigoCliente);
        List<Movimientos> resultList = query.getResultList();
        return resultList;
     }
     
     public double calcularSaldoActual(List<Movimientos> movimientos)
     {
        double saldo=0;
         for (Movimientos movimiento : movimientos) {
              if(movimiento.getTipoMovimiento().equals("CREDITO"))
               {
                   saldo +=movimiento.getValor();
               }else{
                   saldo= saldo - movimiento.getValor();
               }
         }
        return saldo;
     }
    
}
