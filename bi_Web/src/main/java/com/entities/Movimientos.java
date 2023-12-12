package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author davidmac
 */
@Entity
@Table(name = "Movimientos")
@NamedQueries({})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovimientosPK movimientosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private long valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;

    public Movimientos() {
    }

    public Movimientos(MovimientosPK movimientosPK) {
        this.movimientosPK = movimientosPK;
    }

    public Movimientos(MovimientosPK movimientosPK, String tipoMovimiento, long valor, String descripcion) {
        this.movimientosPK = movimientosPK;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Movimientos(int idMovimiento, String idCliente) {
        this.movimientosPK = new MovimientosPK(idMovimiento, idCliente);
    }

    public MovimientosPK getMovimientosPK() {
        return movimientosPK;
    }

    public void setMovimientosPK(MovimientosPK movimientosPK) {
        this.movimientosPK = movimientosPK;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimientosPK != null ? movimientosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.movimientosPK == null && other.movimientosPK != null) || (this.movimientosPK != null && !this.movimientosPK.equals(other.movimientosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bi.entities.Movimientos[ movimientosPK=" + movimientosPK + " ]";
    }
    
}
