package com.md.repository.impl;

import com.md.dto.StatInfo;
import com.md.pojo.Tentant;
import com.md.pojo.User;
import com.md.repository.TentantRepository;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.bind.PrintConversionEvent;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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

    @Override
    public Tentant getTentantByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Tentant t  Where t.username.username=:un");
        q.setParameter("un", username);
        try {
            Tentant tentant = (Tentant) q.getSingleResult();
            Hibernate.initialize(tentant.getFollowSet());
            return tentant;
        }
        catch (NoResultException ex) {
            return new Tentant();
        }
    }

    @Override
    public boolean updateTentant(Tentant tentant) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(tentant);
            return true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUserTentant(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Tentant tentant = s.find(Tentant.class, username);
//        s.evict(tentant);
        return tentant != null;
    }

    @Override
    public boolean updateInfoTentant(Principal user, Tentant tentant) {
        Session s = this.factory.getObject().getCurrentSession();
        Tentant existedTentant = getTentantByUsername(user.getName());

        try {
            if (tentant.getFullName() == null)
                tentant.setFullName(existedTentant.getFullName());
            if (tentant.getPhone() == null)
                tentant.setPhone(existedTentant.getPhone());
            if (tentant.getEmail() == null)
                tentant.setEmail(existedTentant.getEmail());

            s.update(user);
            s.update(tentant);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
