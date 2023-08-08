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
@Table(name = "districts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Districts.findAll", query = "SELECT d FROM Districts d"),
    @NamedQuery(name = "Districts.findByCode", query = "SELECT d FROM Districts d WHERE d.code = :code"),
    @NamedQuery(name = "Districts.findByName", query = "SELECT d FROM Districts d WHERE d.name = :name"),
    @NamedQuery(name = "Districts.findByNameEn", query = "SELECT d FROM Districts d WHERE d.nameEn = :nameEn"),
    @NamedQuery(name = "Districts.findByFullName", query = "SELECT d FROM Districts d WHERE d.fullName = :fullName"),
    @NamedQuery(name = "Districts.findByFullNameEn", query = "SELECT d FROM Districts d WHERE d.fullNameEn = :fullNameEn"),
    @NamedQuery(name = "Districts.findByCodeName", query = "SELECT d FROM Districts d WHERE d.codeName = :codeName")})
public class Districts implements Serializable {

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
    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 255)
    @Column(name = "full_name_en")
    private String fullNameEn;
    @Size(max = 255)
    @Column(name = "code_name")
    private String codeName;
    @JsonIgnore
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    @ManyToOne
    private AdministrativeUnits administrativeUnitId;
    @JsonIgnore
    @JoinColumn(name = "province_code", referencedColumnName = "code")
    @ManyToOne
    private Provinces provinceCode;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtId")
    private Set<Room> roomSet;
    @JsonIgnore
    @OneToMany(mappedBy = "districtCode")
    private Set<Wards> wardsSet;

    public Districts() {
    }

    public Districts(String code) {
        this.code = code;
    }

    public Districts(String code, String name) {
        this.code = code;
        this.name = name;
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

    public AdministrativeUnits getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(AdministrativeUnits administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

    public Provinces getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Provinces provinceCode) {
        this.provinceCode = provinceCode;
    }

    @XmlTransient
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }

    @XmlTransient
    public Set<Wards> getWardsSet() {
        return wardsSet;
    }

    public void setWardsSet(Set<Wards> wardsSet) {
        this.wardsSet = wardsSet;
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
        if (!(object instanceof Districts)) {
            return false;
        }
        Districts other = (Districts) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.Districts[ code=" + code + " ]";
    }
    
}
