package com.dsecurity.controller;

import com.dsecurity.dto.request.BookDto;
import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.Book;
import com.dsecurity.model.Catalog;
import com.dsecurity.repository.IBookRepository;
import com.dsecurity.service.IBookService;
import com.dsecurity.service.ICatalogService;
import com.dsecurity.service.serviceIMPL.BookServiceImIMPL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private BookServiceImIMPL bookServiceImIMPL;
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICatalogService catalogService;

    //    @GetMapping
//    public ResponseEntity<List<Book>> findAll(){
//        List<Book> list = (List<Book>) bookService.findAll();
//        if (list.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }else {
//            return new ResponseEntity<>(list,HttpStatus.OK);
//        }
//    }
    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<Book> list = bookService.getAllByBook(pageable);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('pm') or hasRole('admin')")
    public ResponseEntity<?> create(@RequestBody BookDto bookDto)  {

        String title = bookDto.getTitle();
        Iterable<Book> existingBooks = bookRepository.findBookByTitle(title);
        if (existingBooks.iterator().hasNext()) {
            return new ResponseEntity<>("Book with the same title already exists", HttpStatus.BAD_REQUEST);
        }

        Catalog catalogOptional = catalogService.findById(bookDto.getCatalogId());
        if (catalogOptional == null) {
            return new ResponseEntity<>("Catalog not found", HttpStatus.NOT_FOUND);
        }
        Book newBook = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .bookStatus(bookDto.isBookStatus())
                .imageUrl(bookDto.getImageUrl())
                .catalog(catalogOptional)
                .build();
        Book savedBook = bookService.save(newBook);

        if (savedBook != null) {
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id, @RequestBody Book book) {
        Optional<Book> bookOptional = Optional.ofNullable(bookService.findById(id));
        if (bookOptional.isPresent()) {
            book.setBookId(id);
            bookService.save(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Long id) {

        Optional<Book> bookOptional = Optional.ofNullable(bookService.findById(id));
        if (bookOptional.isPresent()) {
            bookService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        Optional<Book> bookOptional = Optional.ofNullable(bookService.findById(id));
        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<Book>> findByBook(@RequestParam("title") String title) {
        List<Book> list = (List<Book>) bookService.findBookByTitle(title);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
    // phân trang
}
