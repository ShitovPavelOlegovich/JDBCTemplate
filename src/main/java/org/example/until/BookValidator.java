package org.example.until;

import org.example.dao.BookDAO;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (bookDAO.first(book.getTitle()).isPresent())
            errors.rejectValue("title","","Такое название книги уже существует");

        if (bookDAO.second(book.getAuthor()).isPresent())
            errors.rejectValue("author","","Такой автор книги уже существует");
    }
}