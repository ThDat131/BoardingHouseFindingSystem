package com.md.repository.impl;

import com.md.pojo.Tentant;
import com.md.repository.TentantRepository;
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
public class TentantRepositoryImpl implements TentantRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Tentant> getTentants() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From Tentant");
        try {
            return  query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addTentant(Tentant tentant) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(tentant);
            return  true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
