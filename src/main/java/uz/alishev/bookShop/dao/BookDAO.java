package uz.alishev.bookShop.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.alishev.bookShop.model.Book;
import uz.alishev.bookShop.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void appointBook(int id, int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", id,book_id);
    }
    public List<Book> showBook(int id) {
        return jdbcTemplate.query("SELECT id,name,author_name,yearOfBook from Book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT Person.name from Person Left Join Book on person.id = book.person_id where book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name,author_name, yearOfBook) VALUES(?, ?,?)", book.getName(),book.getAuthor_name(), book.getYearOfBook());
    }
    public void update(int id) {
        jdbcTemplate.update("UPDATE Book Set person_id = null where id = ?",id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


}
