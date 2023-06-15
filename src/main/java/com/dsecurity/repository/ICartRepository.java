package com.dsecurity.repository;

import com.dsecurity.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findCartByUserId(Long userId);
}
