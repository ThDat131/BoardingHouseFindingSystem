/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author truon
 */
@Entity
@Table(name = "Tentant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tentant.findAll", query = "SELECT t FROM Tentant t"),
    @NamedQuery(name = "Tentant.findById", query = "SELECT t FROM Tentant t WHERE t.id = :id"),
    @NamedQuery(name = "Tentant.findByFullName", query = "SELECT t FROM Tentant t WHERE t.fullName = :fullName"),
    @NamedQuery(name = "Tentant.findByPhone", query = "SELECT t FROM Tentant t WHERE t.phone = :phone"),
    @NamedQuery(name = "Tentant.findByEmail", query = "SELECT t FROM Tentant t WHERE t.email = :email"),
    @NamedQuery(name = "Tentant.findByPersonalId", query = "SELECT t FROM Tentant t WHERE t.personalId = :personalId")})
public class Tentant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fullName")
    private String fullName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "personalId")
    private String personalId;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "tenantId")
    @JsonIgnore
    private Collection<Follow> followCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @JsonIgnore
    @OneToOne(optional = false)
    private User username;

    public Tentant() {
    }

    public Tentant(String id) {
        this.id = id;
    }

    public Tentant(String id, String fullName, String phone, String email, String personalId, User username) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.personalId = personalId;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @XmlTransient
    public Collection<Follow> getFollowCollection() {
        return followCollection;
    }

    public void setFollowCollection(Collection<Follow> followCollection) {
        this.followCollection = followCollection;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
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
        if (!(object instanceof Tentant)) {
            return false;
        }
        Tentant other = (Tentant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.Tentant[ id=" + id + " ]";
    }
    
}
