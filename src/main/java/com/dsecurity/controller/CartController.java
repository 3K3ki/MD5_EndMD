package com.dsecurity.controller;


import com.dsecurity.dto.request.BookDto;
import com.dsecurity.dto.request.CartDto;
import com.dsecurity.dto.request.CartItemDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;
import com.dsecurity.model.CartItem;
import com.dsecurity.model.Catalog;
import com.dsecurity.service.IBookService;
import com.dsecurity.service.ICartItemService;
import com.dsecurity.service.ICartService;
import javafx.scene.effect.SepiaTone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private final ICartService cartService;
    @Autowired
    private final IBookService bookService;

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        List<Cart> list = (List<Cart>) cartService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/addtoCart/{cartId}")
    public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto, @PathVariable("cartId") Long cartId) {
        Set<CartDto> items = new HashSet<>();

        for (CartItemDto cartItemDto : cartDto.getCartItems()) {
            Book book = bookService.findById(cartItemDto.getBookId());
            CartDto updatedCart = cartService.addBookToCart(cartId, book.getBookId(), cartItemDto.getQuantity());
            items.add(updatedCart);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }



}


