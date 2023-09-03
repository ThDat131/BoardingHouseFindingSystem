package com.md.repository.impl;

import com.md.pojo.Room;
import com.md.pojo.User;
import com.md.repository.RoomRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public boolean addRoom(Room room) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(room);
            return true;
        }
        catch(HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRoom(Room room) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            session.update(room);
            return true;
        } catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Room> getRoomsByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("From Room Where username.username=:un");
        q.setParameter("un", username);

        try {
            return q.getResultList();
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deleteRoom(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        Room room = session.get(Room.class, id);
        try {
            session.delete(room);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Room getRoomById(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            return session.get(Room.class, id);
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
