/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.md.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author truon
 */
@Entity
@Table(name = "User")
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
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Size(min = 1, max = 200)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    @JsonIgnore
    private boolean isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role")
    private int role;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "username")
    private Set<Comment> commentSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "username")
    private Set<Post> postSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "senderId")
    private Set<Notification> notificationSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "receiverId")
    private Set<Notification> notificationSet1;
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "username")
    private LandLord landLord;
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "username")
    private Tentant tentant;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "username")
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
    @JsonIgnore
    private MultipartFile imgUrl;

    public MultipartFile getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(MultipartFile imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Tentant getTentant() {
        return tentant;
    }

    public void setTentant(Tentant tentant) {
        this.tentant = tentant;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }

    public Set<Notification> getNotificationSet() {
        return notificationSet;
    }

    public void setNotificationSet(Set<Notification> notificationSet) {
        this.notificationSet = notificationSet;
    }

    public Set<Notification> getNotificationSet1() {
        return notificationSet1;
    }

    public void setNotificationSet1(Set<Notification> notificationSet1) {
        this.notificationSet1 = notificationSet1;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LandLord getLandLord() {
        return landLord;
    }

    public void setLandLord(LandLord landLord) {
        this.landLord = landLord;
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

    public Set<Room> getRoomSet() {
        return roomSet;
    }

    public void setRoomSet(Set<Room> roomSet) {
        this.roomSet = roomSet;
    }
    
}
