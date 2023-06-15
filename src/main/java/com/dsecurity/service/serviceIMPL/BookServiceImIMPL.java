package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Book;
import com.dsecurity.repository.IBookRepository;
import com.dsecurity.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImIMPL implements IBookService {
    @Autowired
    private final IBookRepository iBookRepository;
    private String uploadImage(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Đường dẫn thư mục lưu trữ hình ảnh
            String uploadDir = "\\Users\\beiu\\Desktop\\MyFile";

            // Tạo tên tệp tin duy nhất cho hình ảnh
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

            // Tạo đối tượng File để lưu hình ảnh
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            File file = new File(uploadPath, fileName);

            // Lưu hình ảnh vào thư mục lưu trữ
            imageFile.transferTo(file);

            // Trả về đường dẫn của hình ảnh đã tải lên
            return uploadDir + "/" + fileName;
        }

        return null;
    }

    @Override
    public boolean existsByBookName(String title) {
        return iBookRepository.existsBookByTitle(title);
    }

    @Override
    public Iterable<Book> findBookByTitle(String title) {
        return iBookRepository.findBookByTitle(title);
    }

    @Override
    public Page<Book> getAllByBook(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 6);
        return iBookRepository.findAll(pageable);
    }

    @Override
    public List<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public Book save(Book book) {

        return iBookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        Book book = iBookRepository.findById(id).get();
        // Thay đổi bookStatus thành false
        book.setBookStatus(false);
        iBookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return iBookRepository.findById(id).get();
    }

    @Override
    public Book update(Long id, Book book) {
        return null;
    }
}
