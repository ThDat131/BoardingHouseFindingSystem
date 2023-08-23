package com.md.repository.impl;

import com.md.pojo.LandLord;
import com.md.pojo.User;
import com.md.repository.LandLordRepository;
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
public class LandLordRepositoryImpl implements LandLordRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LandLord> getLandLords() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From LandLord");
        try {
            return query.getResultList();
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addLandLord(LandLord landLord) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(landLord);
            return true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
