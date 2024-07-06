/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.repository.impl;

import com.tat.hibernate.HibernateUtils;
import com.tat.pojo.User;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class UserRepositoryImpl {

    public User getUserByUsername(String username) {
        try ( Session s = HibernateUtils.getFactory().openSession()) {
            Query q = s.createNamedQuery("User.findByUsername");
            q.setParameter("username", username);

            return (User) q.getSingleResult();
        }
    }
}
