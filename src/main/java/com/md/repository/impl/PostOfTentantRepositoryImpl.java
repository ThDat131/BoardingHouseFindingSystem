package com.md.repository.impl;

import com.md.pojo.PostOfTenant;
import com.md.repository.PostOfTentantRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostOfTentantRepositoryImpl implements PostOfTentantRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<PostOfTenant> getPostsOfTentant() {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query =session.createQuery("SELECT pt FROM PostOfTenant pt join Post p on pt.id = p.id ");
            return query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addPostOfTentant(PostOfTenant post) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
