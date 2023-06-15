package com.dsecurity.repository;

import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;
import com.dsecurity.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartAndBook(Cart cart, Book book);

}
