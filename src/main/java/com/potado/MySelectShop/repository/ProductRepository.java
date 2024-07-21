package com.potado.MySelectShop.repository;

import com.potado.MySelectShop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
