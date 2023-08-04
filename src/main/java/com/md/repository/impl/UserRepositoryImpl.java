package com.md.repository.impl;

import com.md.pojo.User;
import com.md.repository.UserRepository;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public List<User> getUsers() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findAll");

        return q.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From User Where username=:un");
        q.setParameter("un", username);
        try {
            User user = (User) q.getSingleResult();
            return user;
        }
        catch (NoResultException ex) {
            return new User();
        }
    }
    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        try {
            User findedUser = getUserByUsername(user.getUsername());
            if (findedUser.getUsername() == null)
                s.save(user);
            else
            {
                s.clear();
                s.merge(user);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
