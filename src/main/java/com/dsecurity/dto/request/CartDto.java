package com.dsecurity.dto.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CartDto {
    private Long cartId;
    private Long userId;
    private float total;
    private List<CartItemDto> cartItems;


}

