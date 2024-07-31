package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<Book>();

    public BookController() {
        books.add(new Book("Book 1", "Author 1", 1));
        books.add(new Book("Book 2", "Author 2", 2));
    }

    //CREATE

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    // READ
    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book book = books.stream()
                .filter(b -> Objects.equals(b.getId(), id))
                .findFirst()
                .orElse(null);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        }

        return book;
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean ifRemoved=books.removeIf(b -> Objects.equals(b.getId(), id));

        if(ifRemoved) {
            return "Book deleted successfully";
        }else{
            return "Book could not be deleted";
        }
    }

    //ReadAll
    @GetMapping
    public List<Book> getBooks() {
        return books;
    }
}
