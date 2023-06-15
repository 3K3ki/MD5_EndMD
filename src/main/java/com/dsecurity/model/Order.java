package com.dsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NotNull(message = "user is required")
    @ManyToOne
    private User user;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;
    @Min(value = 0, message = "Total price must be a non-negative number")
    private double totalPrice;
}