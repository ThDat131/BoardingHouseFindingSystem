package com.md.repository.impl;

import com.md.dto.StatInfo;
import com.md.repository.StatisticRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class StatisticRepositoryImpl implements StatisticRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<StatInfo> countNumTentantByMonth(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DATE_FORMAT(u.createdDate, '%m-%Y'), COUNT(u.id) FROM User u WHERE u.role = -1 AND u.createdDate BETWEEN :f AND :t GROUP BY DATE_FORMAT(u.createdDate, '%m-%Y') ORDER BY DATE_FORMAT(u.createdDate, '%m-%Y') asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumLandLordByMonth(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DATE_FORMAT(u.createdDate, '%m-%Y'), COUNT(u.id) FROM User u WHERE u.role = 0 AND u.createdDate BETWEEN :f AND :t GROUP BY DATE_FORMAT(u.createdDate, '%m-%Y') ORDER BY DATE_FORMAT(u.createdDate, '%m-%Y') asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumTentantByDay(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DATE_FORMAT(u.createdDate, '%d-%m-%Y'), COUNT(u.id) FROM User u WHERE u.role = -1 AND u.createdDate BETWEEN :f AND :t GROUP BY DATE_FORMAT(u.createdDate, '%d-%m-%Y') ORDER BY DATE_FORMAT(u.createdDate, '%d-%m-%Y') asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumLandLordByDay(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DATE_FORMAT(u.createdDate, '%d-%m-%Y'), COUNT(u.id) FROM User u WHERE u.role = 0 AND u.createdDate BETWEEN :f AND :t GROUP BY DATE_FORMAT(u.createdDate, '%d-%m-%Y') ORDER BY DATE_FORMAT(u.createdDate, '%d-%m-%Y') asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumTentantByQuarter(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT QUARTER(u.createdDate), COUNT(u.id) FROM User u WHERE u.role = -1 AND u.createdDate BETWEEN :f AND :t GROUP BY QUARTER(u.createdDate), YEAR(u.createdDate) ORDER BY QUARTER(u.createdDate) asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumLandLordByQuarter(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT QUARTER(u.createdDate), COUNT(u.id) FROM User u WHERE u.role = 0 AND u.createdDate BETWEEN :f AND :t GROUP BY QUARTER(u.createdDate), YEAR(u.createdDate) ORDER BY QUARTER(u.createdDate) asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumTentantByYear(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT YEAR(u.createdDate), COUNT(u.id) FROM User u WHERE u.role = -1 AND u.createdDate BETWEEN :f AND :t GROUP BY YEAR(u.createdDate) ORDER BY YEAR(u.createdDate) asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<StatInfo> countNumLandLordByYear(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT YEAR(u.createdDate), COUNT(u.id) FROM User u WHERE u.role = 0 AND u.createdDate BETWEEN :f AND :t GROUP BY YEAR(u.createdDate) ORDER BY YEAR(u.createdDate) asc");
            query.setParameter("f", from);
            query.setParameter("t", to);
            List<StatInfo> list = (List<StatInfo>) query.getResultList();
            return list;
        }
        catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
