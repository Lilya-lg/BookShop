package uz.alishev.bookShop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.alishev.bookShop.model.Person;

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
        return jdbcTemplate.query("SELECT Person.id,Person.birthyear,Person.name  from Person LEFT JOIN Book on Person.id = Book.person_id where Person.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Person showToBook(int id) {
        System.out.print(id);
        return jdbcTemplate.query("SELECT *  from Person  where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }


    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birthYear) VALUES(?, ?)", person.getName(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, birthYear=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getBirthYear(), id);
    }



    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
