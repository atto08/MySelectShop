package com.potado.MySelectShop.repository;

import com.potado.MySelectShop.entity.Product;
import com.potado.MySelectShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUser(User user);
}
