/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
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
@Table(name = "LandLord")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LandLord.findAll", query = "SELECT l FROM LandLord l"),
    @NamedQuery(name = "LandLord.findById", query = "SELECT l FROM LandLord l WHERE l.id = :id"),
    @NamedQuery(name = "LandLord.findByFullName", query = "SELECT l FROM LandLord l WHERE l.fullName = :fullName"),
    @NamedQuery(name = "LandLord.findByAddress", query = "SELECT l FROM LandLord l WHERE l.address = :address"),
    @NamedQuery(name = "LandLord.findByPhone", query = "SELECT l FROM LandLord l WHERE l.phone = :phone"),
    @NamedQuery(name = "LandLord.findByPersonalId", query = "SELECT l FROM LandLord l WHERE l.personalId = :personalId")})
public class LandLord implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "personalId")
    private String personalId;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "landLordId")
    private Set<Image> imageSet;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @OneToOne(optional = false)
    private User username;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "landLordId")
    private Set<Follow> followSet;

    public LandLord() {
    }

    public LandLord(String id) {
        this.id = id;
    }

    public LandLord(String id, String fullName, String address, String phone, String personalId, User username) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.personalId = personalId;
        this.username = username;
    }

    public LandLord(String id, String fullName, String address, String phone, String email, String personalId, User user) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.personalId = personalId;
        this.username = user;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @XmlTransient
    public Set<Image> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    @XmlTransient
    public Set<Follow> getFollowSet() {
        return followSet;
    }

    public void setFollowCollection(Set<Follow> followSet) {
        this.followSet = followSet;
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
        if (!(object instanceof LandLord)) {
            return false;
        }
        LandLord other = (LandLord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.LandLord[ id=" + id + " ]";
    }
    
}
