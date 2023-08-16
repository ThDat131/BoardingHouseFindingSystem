package com.md.repository.impl;

import com.md.pojo.Districts;
import com.md.pojo.Provinces;
import com.md.pojo.Wards;
import com.md.repository.AddressRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AddressRepositoryImpl implements AddressRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Provinces getProvinceById(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Provinces.class, id);
    }

    @Override
    public Districts getDistrictById(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Districts.class, id);
    }

    @Override
    public Wards getWardById(String id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Wards.class, id);
    }

    @Override
    public List<Provinces> getProvinces() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createNamedQuery("Provinces.findAll");
        return query.getResultList();
    }

    @Override
    public List<Districts> getDistrictByProvincesCode(String code) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("from Districts where provinceCode.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public List<Wards> getWardsByDistrictCode(String code) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("from Wards where districtCode.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }
}
