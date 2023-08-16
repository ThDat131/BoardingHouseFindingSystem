/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author truon
 */
@Entity
@Table(name = "Room")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findById", query = "SELECT r FROM Room r WHERE r.id = :id"),
    @NamedQuery(name = "Room.findByAddress", query = "SELECT r FROM Room r WHERE r.address = :address"),
    @NamedQuery(name = "Room.findByLongitude", query = "SELECT r FROM Room r WHERE r.longitude = :longitude"),
    @NamedQuery(name = "Room.findByLatitude", query = "SELECT r FROM Room r WHERE r.latitude = :latitude"),
    @NamedQuery(name = "Room.findByPrice", query = "SELECT r FROM Room r WHERE r.price = :price"),
    @NamedQuery(name = "Room.findByAcreage", query = "SELECT r FROM Room r WHERE r.acreage = :acreage")})
public class Room implements Serializable {

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
    @Column(name = "address")
    private String address;
    @Size(max = 200)
    @Column(name = "longitude")
    private String longitude;
    @Size(max = 200)
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acreage")
    private float acreage;
    @JsonIgnore
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;
    @JoinColumn(name = "district_id", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Districts districtId;
    @JoinColumn(name = "province_id", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Provinces provinceId;
    @JoinColumn(name = "ward_id", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Wards wardId;

    public Room() {
    }

    public Room(String id) {
        this.id = id;
    }

    public Room(String id, String address, float price, float acreage) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.acreage = acreage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAcreage() {
        return acreage;
    }

    public void setAcreage(float acreage) {
        this.acreage = acreage;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Districts getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Districts districtId) {
        this.districtId = districtId;
    }

    public Provinces getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Provinces provinceId) {
        this.provinceId = provinceId;
    }

    public Wards getWardId() {
        return wardId;
    }

    public void setWardId(Wards wardId) {
        this.wardId = wardId;
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
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.Room[ id=" + id + " ]";
    }
    
}
