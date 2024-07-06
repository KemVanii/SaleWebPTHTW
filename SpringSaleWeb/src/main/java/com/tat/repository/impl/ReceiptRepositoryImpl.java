package com.tat.repository.impl;

import com.tat.hibernate.HibernateUtils;
import com.tat.pojo.Cart;
import com.tat.pojo.OrderDetail;
import com.tat.pojo.SaleOrder;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
public class ReceiptRepositoryImpl {
    private static final UserRepositoryImpl u = new UserRepositoryImpl();
    private static final ProductRepositoryImpl pr = new ProductRepositoryImpl();
    public void addReceipt(List<Cart> carts) {
        if (carts != null) {
            try(Session s = HibernateUtils.getFactory().openSession()) {
                SaleOrder sale = new SaleOrder();
                sale.setUserId(u.getUserByUsername("dhthanh"));
                sale.setCreatedDate(new Date());
                s.save(sale);
                
                for(var c:carts) {
                    OrderDetail detail = new OrderDetail();
                    detail.setUnitPrice(c.getUnitPrice());
                    detail.setQuantity(c.getQuantity());
                    detail.setProductId(pr.getProductById(c.getId()));
                    detail.setOrderId(sale);
                    
                    s.save(detail);
                }
            }
        }
    }
}
