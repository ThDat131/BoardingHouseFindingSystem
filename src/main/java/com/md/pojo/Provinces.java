/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "provinces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provinces.findAll", query = "SELECT p FROM Provinces p"),
    @NamedQuery(name = "Provinces.findByCode", query = "SELECT p FROM Provinces p WHERE p.code = :code"),
    @NamedQuery(name = "Provinces.findByName", query = "SELECT p FROM Provinces p WHERE p.name = :name"),
    @NamedQuery(name = "Provinces.findByNameEn", query = "SELECT p FROM Provinces p WHERE p.nameEn = :nameEn"),
    @NamedQuery(name = "Provinces.findByFullName", query = "SELECT p FROM Provinces p WHERE p.fullName = :fullName"),
    @NamedQuery(name = "Provinces.findByFullNameEn", query = "SELECT p FROM Provinces p WHERE p.fullNameEn = :fullNameEn"),
    @NamedQuery(name = "Provinces.findByCodeName", query = "SELECT p FROM Provinces p WHERE p.codeName = :codeName")})
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "name_en")
    private String nameEn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 255)
    @Column(name = "full_name_en")
    private String fullNameEn;
    @Size(max = 255)
    @Column(name = "code_name")
    private String codeName;
    @JsonIgnore
    @JoinColumn(name = "administrative_region_id", referencedColumnName = "id")
    @ManyToOne
    private AdministrativeRegions administrativeRegionId;
    @JsonIgnore
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    @ManyToOne
    private AdministrativeUnits administrativeUnitId;
    @JsonIgnore
    @OneToMany(mappedBy = "provinceCode")
    private Set<Districts> districtsSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceId")
    private Set<Room> roomSet;

    public Provinces() {
    }

    public Provinces(String code) {
        this.code = code;
    }

    public Provinces(String code, String name, String fullName) {
        this.code = code;
        this.name = name;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public AdministrativeRegions getAdministrativeRegionId() {
        return administrativeRegionId;
    }

    public void setAdministrativeRegionId(AdministrativeRegions administrativeRegionId) {
        this.administrativeRegionId = administrativeRegionId;
    }

    public AdministrativeUnits getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(AdministrativeUnits administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

    @XmlTransient
    public Set<Districts> getDistrictsSet() {
        return districtsSet;
    }

    public void setDistrictsSet(Set<Districts> districtsSet) {
        this.districtsSet = districtsSet;
    }

    @XmlTransient
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provinces)) {
            return false;
        }
        Provinces other = (Provinces) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.Provinces[ code=" + code + " ]";
    }
    
}
