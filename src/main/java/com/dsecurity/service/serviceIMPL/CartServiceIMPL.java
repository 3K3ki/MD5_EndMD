package com.dsecurity.service.serviceIMPL;

import com.dsecurity.dto.request.CartDto;
import com.dsecurity.dto.request.CartItemDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;
import com.dsecurity.model.CartItem;
import com.dsecurity.repository.IBookRepository;
import com.dsecurity.repository.ICartItemRepository;
import com.dsecurity.repository.ICartRepository;
import com.dsecurity.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CartServiceIMPL implements ICartService {
    @Autowired
    private final ICartRepository cartRepository;
    @Autowired
    private final IBookRepository bookRepository;
    @Autowired
    private final ICartItemRepository cartItemRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart update(Long id, Cart cart) {
        return null;
    }


    @Override
    public Cart findCartByUserId(Long userId) {
        try {
            return cartRepository.findCartByUserId(userId).get();

        }catch (NoSuchElementException e) {
            System.out.println("không tìm thấy cart");
            return null;
        }
    }

    @Override
    public CartDto addBookToCart(Long cartId, Long bookId,int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book);
        if (cartItem == null) {
            cartItem = CartItem.builder()
                    .cart(cart)
                    .book(book)
                    .quantity(quantity)
                    .build();
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);

        cart.setTotal(calculateCartTotal(cart));
        cartRepository.save(cart);

        CartDto cartDto = convertToCartDTO(cart);
        List<CartItemDto> cartItemDtos = convertToCartItemDtoList(cart.getCartItems());
        cartDto.setCartItems(cartItemDtos);

        return cartDto;
    }

    private float calculateCartTotal(Cart cart) {
        float total = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getBook().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public CartDto convertToCartDTO(Cart cart) {
        CartDto cartDto = CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUser().getId())
                .total(cart.getTotal())
                .cartItems(convertToCartItemDtoList(cart.getCartItems()))
                .build();
        return cartDto;
    }

//
private List<CartItemDto> convertToCartItemDtoList(List<CartItem> cartItems) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemDto cartItemDto = convertToCartItemDto(cartItem);
            cartItemDtos.add(cartItemDto);
        }
        return cartItemDtos;
    }
    private CartItemDto convertToCartItemDto(CartItem cartItem) {
        Book book = cartItem.getBook();

        CartItemDto cartItemDto = CartItemDto.builder()
                .cartItemId(cartItem.getCartItemId())
                .cartId(cartItem.getCart().getCartId())
                .bookId(book.getBookId())
                .quantity(cartItem.getQuantity())
                .build();

        return cartItemDto;
    }
}
