package com.md.repository.impl;

import com.md.pojo.LandLord;
import com.md.pojo.Tentant;
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
import java.security.Principal;
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

    @Override
    public LandLord getLandLordByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From LandLord Where username.username=:un");
        q.setParameter("un", username);
        try {
            LandLord landLord = (LandLord) q.getSingleResult();
//            s.evict(landLord);
            return landLord;
        }
        catch (NoResultException ex) {
            return new LandLord();
        }
    }

    @Override
    public boolean updateLandLord(LandLord landLord) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(landLord);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public LandLord getLandLordById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            LandLord l = s.get(LandLord.class, id);
            return l;

        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<LandLord> getLandLordsInactive() {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT distinct f FROM LandLord f left join fetch f.imageSet where f.username.isActive = false");
            return query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isUserLandLord(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        LandLord landLord = s.find(LandLord.class, username);
        s.evict(landLord);
        return landLord != null;
    }

    @Override
    public boolean updateInfoLandLord(Principal user, LandLord landLord) {
        Session s = this.factory.getObject().getCurrentSession();
        LandLord existedLandLord = getLandLordByUsername(user.getName());

        try {
            if (landLord.getFullName() == null)
                landLord.setFullName(existedLandLord.getFullName());
            if (landLord.getPhone() == null)
                landLord.setPhone(existedLandLord.getPhone());
            if (landLord.getEmail() == null)
                landLord.setEmail(existedLandLord.getEmail());
            if (landLord.getAddress() == null)
                landLord.setAddress(existedLandLord.getAddress());

            s.update(user);
            s.update(landLord);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
