package com.dsecurity.service;

import com.dsecurity.dto.request.BookDto;
import com.dsecurity.dto.request.CartDto;
import com.dsecurity.dto.request.CartItemDto;
import com.dsecurity.model.Book;
import com.dsecurity.model.Cart;
import com.dsecurity.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBookService extends IGenereicService<Book,Long>{
    boolean existsByBookName(String title);
    Iterable<Book> findBookByTitle( String title);
    Page<Book> getAllByBook(Pageable pageable);
    

}
