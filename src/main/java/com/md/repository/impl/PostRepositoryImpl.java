package com.md.repository.impl;

import com.md.pojo.Image;
import com.md.pojo.Post;
import com.md.repository.PostRepository;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public boolean addLandLordPost(Post post) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(post);
            return true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Post> getPosts() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("Select distinct p From Post p left join fetch p.imageSet order by p.createdDate desc ");
        try {
            return query.getResultList();
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Post getPostById(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Post post =  session.get(Post.class, id);
            Hibernate.initialize(post.getImageSet());
            return post;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
