/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author truon
 */
@Entity
@Table(name = "Follow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Follow.findAll", query = "SELECT f FROM Follow f"),
    @NamedQuery(name = "Follow.findById", query = "SELECT f FROM Follow f WHERE f.id = :id")})
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "landLordId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LandLord landLordId;
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tentant tenantId;

    public Follow() {
    }

    public Follow(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LandLord getLandLordId() {
        return landLordId;
    }

    public void setLandLordId(LandLord landLordId) {
        this.landLordId = landLordId;
    }

    public Tentant getTenantId() {
        return tenantId;
    }

    public void setTenantId(Tentant tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follow)) {
            return false;
        }
        Follow other = (Follow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.Follow[ id=" + id + " ]";
    }
    
}
