package com.potado.MySelectShop.repository;

import com.potado.MySelectShop.entity.ApiUseTime;
import com.potado.MySelectShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}