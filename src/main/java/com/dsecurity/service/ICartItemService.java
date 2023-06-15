package com.dsecurity.service;

import com.dsecurity.dto.request.BookDto;
import com.dsecurity.dto.request.CartItemDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.CartItem;

public interface ICartItemService extends IGenereicService<CartItem, Long>{

    CartItemDto createCartItem(CartItemDto cartItemDTO);
    void updateCartItemQuantity(Long cartItemId, int quantity);
//    CartItem addBookToCartItem(CartItem cartItem, Book book);

}
