package com.md.repository.impl;

import com.md.pojo.Comment;
import com.md.repository.CommentRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public boolean addComment(Comment comment) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            session.save(comment);
            return true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
