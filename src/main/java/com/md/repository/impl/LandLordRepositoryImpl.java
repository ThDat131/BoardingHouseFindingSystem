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
        Query q = s.createQuery("From LandLord Where username=:un");
        q.setParameter("un", username);
        try {
            LandLord landLord = (LandLord) q.getSingleResult();
            s.evict(landLord);
            return landLord;
        }
        catch (NoResultException ex) {
            return new LandLord();
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
    public boolean updateInfoLandLord(User user, LandLord landLord) {
        Session s = this.factory.getObject().getCurrentSession();
        LandLord existedLandLord = getLandLordByUsername(user.getUsername());

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
