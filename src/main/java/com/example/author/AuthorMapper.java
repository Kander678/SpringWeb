package com.example.author;

import com.example.book.BookDTO;
import com.example.book.BookEntity;

// TODO - usun mapper, a metody dodaj do odpowiednich POJOs
// TODO - optymalizacji importow
public class AuthorMapper {

    public static AuthorDTO toDTO(AuthorEntity author){
        AuthorDTO dto=new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        return dto;
    }
    public static AuthorEntity toEntity(AuthorDTO dto){
        AuthorEntity entity=new AuthorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        return entity;
    }
}
