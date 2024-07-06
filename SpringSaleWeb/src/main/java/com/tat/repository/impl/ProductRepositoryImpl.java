/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.hibernate.HibernateUtils;
import com.tat.pojo.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class ProductRepositoryImpl {

    private static final int PAGE_SIZE = 4;

    public List<Product> getProds(Map<String, String> params) {

        try ( Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                String kw = params.get("kw");
                if (kw != null && !kw.isEmpty()) {
                    Predicate p = b.like(root.get("name"), String.format("%%%s%%", kw));
                    predicates.add(p);
                }

                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty()) {
                    Predicate p = b.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                    predicates.add(p);
                }

                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty()) {
                    Predicate p = b.lessThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice));
                    predicates.add(p);
                }

                String cateId = params.get("cateId");
                if (cateId != null && !cateId.isEmpty()) {
                    Predicate p = b.equal(root.get("category"), Integer.parseInt(cateId));
                    predicates.add(p);
                }

                q.where(predicates.toArray(Predicate[]::new));
            }

            Query query = s.createQuery(q);

            if (params != null) {
                String page = params.get("page");
                if (page != null && !page.isEmpty()) {
                    int p = Integer.parseInt(page);
                    int start = (p - 1) * PAGE_SIZE;

                    query.setFirstResult(start);
                    query.setMaxResults(PAGE_SIZE);
                }
            }

            return query.getResultList();
        }
    }

    public void addOrUpdate(Product p) {
        try ( Session s = HibernateUtils.getFactory().openSession()) {
            if (p.getId() != null) {
                s.update(s);
            } else {
                s.save(s);
            }
        }
    }

    public Product getProductById(int id) {
        try ( Session s = HibernateUtils.getFactory().openSession()) {
            return s.get(Product.class, id);
        }
    }
}
