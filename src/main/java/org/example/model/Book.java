package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int idBook;
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов длиной")
    private String title;
    @NotEmpty(message = "Автор книги не должен быть пустым")
    @Size(min = 2, max = 100, message = "Автор книги должно быть от 2 до 100 символов длиной")
    private String author;
    @Min(value = 1700, message = "Год должен быть больше, чем 1700")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
