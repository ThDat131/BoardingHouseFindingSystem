package com.md.repository.impl;

import com.md.pojo.Image;
import com.md.repository.ImageRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImageRepositoryImpl implements ImageRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addImage(Image image) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(image);
            return true;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
