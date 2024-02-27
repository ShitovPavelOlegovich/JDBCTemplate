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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id_person=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_birthday) VALUES(?,?)",
                person.getFullName(), person.getYearBirthday());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_birthday=? WHERE id_person=?",
                updatePerson.getFullName(), updatePerson.getYearBirthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id_person=?", id);
    }

    public List<Book> getBookByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getPersonByFullName(String fullName)
    {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

}
