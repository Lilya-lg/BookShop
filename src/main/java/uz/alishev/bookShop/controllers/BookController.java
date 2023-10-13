package uz.alishev.bookShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.alishev.bookShop.dao.BookDAO;
import uz.alishev.bookShop.model.Book;
import uz.alishev.bookShop.dao.PersonDAO;
import uz.alishev.bookShop.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personN",bookDAO.showPerson(id));
        model.addAttribute("people",personDAO.index());
        return "books/show";
    }
    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}/delete")
    public String deleteNote(@PathVariable("id") int id){
        bookDAO.update(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String makeAdmin(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        bookDAO.appointBook(person.getId(),id);
        return "redirect:/books";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
