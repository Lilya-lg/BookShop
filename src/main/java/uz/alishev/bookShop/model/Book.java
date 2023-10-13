package uz.alishev.bookShop.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message="Name should not be empty")
    @Size(min=2,max=30,message = "Name should be between 2 and 30")
    private String name;

    private String author_name;
    private int yearOfBook;

    public Book() {
    }

    public Book(int id, String name, String author_name, int yearOfBook) {
        this.id = id;
        this.name = name;
        this.author_name = author_name;
        this.yearOfBook = yearOfBook;
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

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getYearOfBook() {
        return yearOfBook;
    }

    public void setYearOfBook(int yearOfBook) {
        this.yearOfBook = yearOfBook;
    }

}
