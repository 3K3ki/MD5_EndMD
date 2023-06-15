package com.dsecurity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long bookId;
    private String title;
    private String author;
    private double price;

    private String imageUrl;
    private boolean bookStatus = true;
    private Long catalogId;
}
