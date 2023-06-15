package com.dsecurity.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotBlank(message =  "The book title is required")
    private String title;
    @NotBlank(message =  "The book author is required")
    private String author;
//    @NotBlank(message =  "The book title is r vcequired")
    @Min(value = 0, message = "Price must be a positive number")
    private double price;
    @NotBlank(message =  "The book image is required")
    @Lob
    private String imageUrl;
    @Column(nullable = false)
    private boolean bookStatus = true;
    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;
}
