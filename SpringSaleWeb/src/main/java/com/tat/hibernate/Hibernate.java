/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.hibernate;

import com.tat.pojo.Cart;
import com.tat.repository.impl.CategoryRepositoryImpl;
import com.tat.repository.impl.ProductRepositoryImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Hibernate {
    public static void main(String[] args) {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1,"A",2,100l));
//        CategoryRepositoryImpl cates = new CategoryRepositoryImpl();
//        cates.getCates().forEach(cate -> System.out.println(cate.getName()));
//        Map<String, String> params = new HashMap<>();
//        params.put("kw", "Iphone");
//        params.put("page", "3");
//        ProductRepositoryImpl s = new ProductRepositoryImpl();
//        s.getProds(params).forEach(p -> System.out.printf("%s - %.1f\n", p.getName(), p.getPrice()));
    }
}
