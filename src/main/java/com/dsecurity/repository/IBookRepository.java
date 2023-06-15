package com.dsecurity.repository;

import com.dsecurity.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    boolean existsBookByTitle(String title);

    @Query ("Select b from Book  as b where b.title like concat('%',:title,'%')")
    Iterable<Book> findBookByTitle(@Param("title") String title);
    Optional<Book> findByImageUrl(String fileName);
}
