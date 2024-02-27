package org.example.dao;

import org.example.model.Book;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_book=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id_book=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id_book=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id_book=?",
                selectedPerson.getIdPerson(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET id_person=NULL WHERE id_book=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.id_person=Person.id_person WHERE Book.id_book=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    public Optional<Book> first(String title)
    {
        return jdbcTemplate.query("SELECT * FROM Book WHERE title=?", new Object[]{title},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Optional<Book> second(String author)
    {
        return jdbcTemplate.query("SELECT * FROM Book WHERE author=?", new Object[]{author},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

}
