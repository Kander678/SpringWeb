package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        bookRepository.save(new Book(1, "Book 1", "Author 1"));
        bookRepository.save(new Book(2, "Book 2", "Author 2"));
    }

    //CREATE
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // READ
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            bookRepository.save(book);
        }

        return book;
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted";
        }else {
            return "Book not found";
        }
    }

    //ReadAll
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
