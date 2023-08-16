/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Size(min = 1, max = 200)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role")
    private int role;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Comment> commentCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Post> postCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private Collection<Notification> notificationCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverId")
    private Collection<Notification> notificationCollection1;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<LandLord> landLordCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Tentant> tentantCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Set<Room> roomSet;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String avatar, Date createdDate, boolean isActive, int role) {
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.role = role;
    }

    @Transient
    private MultipartFile imgUrl;

    public MultipartFile getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(MultipartFile imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection1() {
        return notificationCollection1;
    }

    public void setNotificationCollection1(Collection<Notification> notificationCollection1) {
        this.notificationCollection1 = notificationCollection1;
    }

    @XmlTransient
    public Collection<LandLord> getLandLordCollection() {
        return landLordCollection;
    }

    public void setLandLordCollection(Collection<LandLord> landLordCollection) {
        this.landLordCollection = landLordCollection;
    }

    @XmlTransient
    public Collection<Tentant> getTentantCollection() {
        return tentantCollection;
    }

    public void setTentantCollection(Collection<Tentant> tentantCollection) {
        this.tentantCollection = tentantCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.md.pojo.User[ username=" + username + " ]";
    }

    @XmlTransient
    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }
    
}
