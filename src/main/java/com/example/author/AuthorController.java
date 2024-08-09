package com.example.author;

import com.example.book.BookDTO;
import com.example.book.BookEntity;
import com.example.book.BookMapper;
import com.example.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO uzywaj formatowania z intellij na kazdym pliku na ktorym pracujesz ( tzw. linter, w zespole moga miec swoje formatowania i beda sie czepiac o to ) - Ctrl+Alt+L
// TODO uzwaj optymalizacji importow: Ctrl+Alt+O
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorEntity authorEntity=AuthorMapper.toEntity(authorDTO);
        AuthorEntity savedAuthorEntity=authorRepository.save(authorEntity);
        return AuthorMapper.toDTO(savedAuthorEntity);
    }

    // READ

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable int id) {
        Optional<AuthorEntity> authorEntity=authorRepository.findById(id);
        return authorEntity.map(AuthorMapper::toDTO).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable int id, @RequestBody AuthorEntity updatedAuthor) {
        // TODO - to samo - uzywaj optionale
        AuthorEntity author = authorRepository.findById(id).orElse(null);

        if (author != null) {
            AuthorEntity authorEntity= author;
            authorEntity.setName(updatedAuthor.getName());
            authorEntity.setSurname(updatedAuthor.getSurname());
            AuthorEntity savedAuthorEntity=authorRepository.save(authorEntity);
            return AuthorMapper.toDTO(savedAuthorEntity);
        }

        return null;
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id) {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return "Author deleted";
        }else {
            return "Author not found";
        }
    }

    //ReadAll
    @GetMapping
    public List<AuthorDTO> getBooks() {
        List<AuthorEntity> author = authorRepository.findAll();
        return author.stream()
                .map(AuthorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
