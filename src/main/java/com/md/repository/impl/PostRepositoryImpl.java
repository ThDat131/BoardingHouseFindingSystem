package com.md.repository.impl;

import com.md.pojo.*;
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
import javax.persistence.criteria.*;
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
        Query query = session.createQuery("Select distinct p From Post p left join fetch p.imageSet left join fetch p.commentSet WHERE p.roomId is not null order by p.createdDate desc ");
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
            Post post  = session.createQuery("FROM Post p left join fetch p.commentSet WHERE p.id=:id",Post.class)
                .setParameter("id", id)
                .getSingleResult();
//            Post post =  session.get(Post.class, id);
            Hibernate.initialize(post.getImageSet());
//            Hibernate.initialize(post.getCommentSet());
            return post;
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getPostOfTentant() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("Select distinct p From Post p left join fetch p.imageSet left join fetch p.postOfTenant left join fetch p.commentSet WHERE p.roomId is null order by p.createdDate desc ");
        try {
            return query.getResultList();
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Post> getPosts(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Post> q = b.createQuery(Post.class);
        Root r = q.from(Post.class);
        r.fetch("commentSet", JoinType.LEFT);
        r.fetch("imageSet", JoinType.LEFT);
        q.distinct(true);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {

            String provinceCode = params.get("province_id");
            if (provinceCode != null && !provinceCode.isEmpty()) {
                Join<Post, Room> roomJoin = r.join("roomId", JoinType.INNER);
                Join<Room, Provinces> provinceJoin = roomJoin.join("provinceId", JoinType.INNER);
                predicates.add(b.equal(provinceJoin.get("code"), provinceCode));
            }

            String districtCode = params.get("district_id");
            if (districtCode != null && !districtCode.isEmpty()) {
                Join<Post, Room> roomJoin = r.join("roomId", JoinType.INNER);
                Join<Room, Districts> districtsJoin = roomJoin.join("districtId", JoinType.INNER);
                predicates.add(b.equal(districtsJoin.get("code"), districtCode));
            }

            String wardCode = params.get("ward_id");
            if (wardCode != null && !wardCode.isEmpty()) {
                Join<Post, Room> roomJoin = r.join("roomId", JoinType.INNER);
                Join<Room, Wards> wardsJoin = roomJoin.join("wardId", JoinType.INNER);
                predicates.add(b.equal(wardsJoin.get("code"), wardCode));
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                Join<Post, Room> roomJoin = r.join("roomId", JoinType.INNER);
                predicates.add(b.greaterThanOrEqualTo(roomJoin.get("price"), Float.parseFloat(fromPrice)));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                Join<Post, Room> roomJoin = r.join("roomId", JoinType.INNER);
                predicates.add(b.lessThanOrEqualTo(roomJoin.get("price"), Float.parseFloat(toPrice)));
            }



        }

        predicates.add(b.isNotNull(r.get("roomId")));

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("createdDate")));
        Query query = session.createQuery(q);
        return query.getResultList();
    }
}
