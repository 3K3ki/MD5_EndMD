package com.dsecurity.service.serviceIMPL;

import com.dsecurity.dto.request.BookDto;
import com.dsecurity.dto.request.CartDto;
import com.dsecurity.dto.request.CartItemDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;
import com.dsecurity.model.CartItem;
import com.dsecurity.repository.IBookRepository;
import com.dsecurity.repository.ICartItemRepository;
import com.dsecurity.repository.ICartRepository;
import com.dsecurity.service.IBookService;
import com.dsecurity.service.ICartItemService;
import com.dsecurity.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceIMPL implements ICartItemService {
    @Autowired
    private final ICartItemRepository cartItemRepository;
    @Autowired
    private final ICartService cartService;
    @Autowired
    private final IBookService bookService;
    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public CartItem update(Long id, CartItem cartItem) {
        return null;
    }

    @Override
    public CartItemDto createCartItem(CartItemDto cartItemDTO) {
        return null;
    }
    @Override
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
    }
//    @Override
//    public CartItem addBookToCartItem(CartItem cartItem, Book book) {
//        cartItem.setBook(book);
//        return cartItemRepository.save(cartItem);
//    }
}
