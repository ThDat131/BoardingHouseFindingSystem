package com.md.repository.impl;

import com.md.pojo.Room;
import com.md.repository.RoomRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

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
}
