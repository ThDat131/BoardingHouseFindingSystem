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
@Table(name = "PostOfTenant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostOfTenant.findAll", query = "SELECT p FROM PostOfTenant p"),
    @NamedQuery(name = "PostOfTenant.findById", query = "SELECT p FROM PostOfTenant p WHERE p.id = :id"),
    @NamedQuery(name = "PostOfTenant.findByAddressToRent", query = "SELECT p FROM PostOfTenant p WHERE p.addressToRent = :addressToRent")})
public class PostOfTenant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "addressToRent")
    private String addressToRent;
    @JoinColumn(name = "postId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post postId;

    public PostOfTenant() {
    }

    public PostOfTenant(String id) {
        this.id = id;
    }

    public PostOfTenant(String id, String addressToRent) {
        this.id = id;
        this.addressToRent = addressToRent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressToRent() {
        return addressToRent;
    }

    public void setAddressToRent(String addressToRent) {
        this.addressToRent = addressToRent;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
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
        if (!(object instanceof PostOfTenant)) {
            return false;
        }
        PostOfTenant other = (PostOfTenant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.PostOfTenant[ id=" + id + " ]";
    }
    
}
