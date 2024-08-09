package com.example.author;

import jakarta.persistence.*;

// TODO poczytaj o lomboku - tutaj mozna uzyc @Data lub @Value, albo po prostu @Getter i @Setter
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    public AuthorEntity(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AuthorEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
