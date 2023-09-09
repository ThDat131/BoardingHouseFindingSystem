package com.md.repository.impl;

import com.md.pojo.Follow;
import com.md.pojo.Tentant;
import com.md.repository.FollowRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Repository
@Transactional
public class FollowRepositoryImpl implements FollowRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public boolean addFollow(Follow follow) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(follow);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteFollow(String landLordId, String tentantId) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("DELETE FROM Follow f WHERE f.landLordId.id=:landLordId AND f.tenantId.id=:tentantId");
            query.setParameter("landLordId", landLordId);
            query.setParameter("tentantId", tentantId);
            return query.executeUpdate() > 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Tentant> getFollowerByLandLordId(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT f.tenantId FROM Follow f WHERE f.landLordId.id =: id");
            query.setParameter("id", id);
            return query.getResultList();
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
