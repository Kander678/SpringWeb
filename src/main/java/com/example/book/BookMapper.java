package com.example.book;

public class BookMapper {

    public static BookDTO toDTO(BookEntity book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        return dto;
    }

    public static BookEntity toEntity(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        return entity;
    }

}
