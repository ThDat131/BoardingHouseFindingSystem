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
    public List<StatInfo> countNumTentantByDate(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT MONTH(u.createdDate), COUNT(u) FROM User u WHERE u.role = -1 AND u.createdDate BETWEEN :f AND :t GROUP BY MONTH(u.createdDate)");
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
    public List<StatInfo> countNumLandLordByDate(Date from, Date to) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Query query = session.createQuery("SELECT MONTH(u.createdDate), COUNT(u) FROM User u WHERE u.role = 0 AND u.createdDate BETWEEN :f AND :t GROUP BY MONTH(u.createdDate)");
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
