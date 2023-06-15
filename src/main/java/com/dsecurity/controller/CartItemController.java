package com.dsecurity.controller;

import com.dsecurity.model.Book;
import com.dsecurity.model.CartItem;
import com.dsecurity.service.IBookService;
import com.dsecurity.service.ICartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IBookService bookService;

//    @PostMapping("/{cartItemId}/addBook/{bookId}")
//    public ResponseEntity<String> addBookToCartItem(@PathVariable("cartItemId") Long cartItemId, @PathVariable("bookId") Long bookId) {
//        CartItem cartItem = cartItemService.findById(cartItemId);
//        Book book = bookService.findById(bookId);
//
//        if (cartItem == null) {
//            return ResponseEntity.badRequest().body("Invalid cart item ID");
//        }
//
//        if (book == null) {
//            return ResponseEntity.badRequest().body("Invalid book ID");
//        }
//
//        cartItemService.addBookToCartItem(cartItem, book);
//        return ResponseEntity.ok("Book added to cart item successfully");
//    }
}
