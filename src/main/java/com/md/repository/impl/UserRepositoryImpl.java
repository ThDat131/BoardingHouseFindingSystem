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
import java.util.Date;
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
            s.evict(user);
            return user;
        }
        catch (NoResultException ex) {
            return new User();
        }
    }

    @Override
    public boolean isUserExits(String username) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        User user = s.find(User.class, username);
        s.evict(user);
        return user != null;
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        User existedUser = getUserByUsername(user.getUsername());
        try {
            if (existedUser.getUsername() == null) {
                user.setCreatedDate(new Date());
                s.save(user);
            }
            else {
                if (user.getPassword() == null)
                    user.setPassword(existedUser.getPassword());
                if (user.getAvatar() == null)
                    user.setAvatar(existedUser.getAvatar());
                if (user.getCreatedDate() == null)
                    user.setCreatedDate(existedUser.getCreatedDate());

                s.update(user);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


}
