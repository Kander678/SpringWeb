package com.example.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO uzywaj formatowania z intellij na kazdym pliku na ktorym pracujesz ( tzw. linter, w zespole moga miec swoje formatowania i beda sie czepiac o to ) - Ctrl+Alt+L
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    //CREATE
    @PostMapping
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {

        BookEntity bookEntity=BookMapper.toEntity(bookDTO);
        BookEntity savedBookEntity=bookRepository.save(bookEntity);
        return BookMapper.toDTO(savedBookEntity);
    }

    // READ
    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable int id) {
        Optional<BookEntity> bookEntity=bookRepository.findById(id);
        return bookEntity.map(BookMapper::toDTO).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable int id, @RequestBody BookEntity updatedBook) {
        /* TODO - findById zwraca optionala, nie rob tak, ze jak dostajes optionala i go nie ma to zwracasz nulla,
         bo to przeczy idei optionali. Zamiast orElse(null) zrob .map() i tam logike z ifa i wypadaloby zrobic sensowny return zamiast nulla.
         */
        BookEntity book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            BookEntity bookEntity= book;
            bookEntity.setAuthor(updatedBook.getAuthor());
            bookEntity.setTitle(updatedBook.getTitle());
            // TODO dodaj relacje w bazie danych miedzy ksiazka a autorem
            BookEntity savedBookEntity=bookRepository.save(bookEntity);
            return BookMapper.toDTO(savedBookEntity);
        }

        return null;
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
    public List<BookDTO> getBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
