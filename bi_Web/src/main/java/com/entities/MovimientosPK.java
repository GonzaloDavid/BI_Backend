package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author davidmac
 */
@Embeddable
public class MovimientosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idMovimiento")
    private int idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idCliente")
    private String idCliente;

    public MovimientosPK() {
    }

    public MovimientosPK(int idMovimiento, String idCliente) {
        this.idMovimiento = idMovimiento;
        this.idCliente = idCliente;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimiento;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovimientosPK)) {
            return false;
        }
        MovimientosPK other = (MovimientosPK) object;
        if (this.idMovimiento != other.idMovimiento) {
            return false;
        }
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bi.entities.MovimientosPK[ idMovimiento=" + idMovimiento + ", idCliente=" + idCliente + " ]";
    }
    
}
