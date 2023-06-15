package com.dsecurity.service;

import com.dsecurity.dto.request.CartDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;

import java.util.List;

public interface ICartService extends IGenereicService<Cart,Long>{
    Cart findCartByUserId(Long userId);
    CartDto addBookToCart(Long cartId, Long bookId,int quantity);

    CartDto convertToCartDTO(Cart cart);
//    Cart addBookToCart(Cart cart, Book book);

}
