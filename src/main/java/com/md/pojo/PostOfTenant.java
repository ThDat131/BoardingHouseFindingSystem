/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author truon
 */
@Entity
@Table(name = "PostOfTenant")
@NamedQueries({
    @NamedQuery(name = "PostOfTenant.findAll", query = "SELECT p FROM PostOfTenant p")})
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
    @OneToOne
    @JsonIgnore
    @MapsId
    @JoinColumn(name = "id")
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

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
