package uz.alishev.bookShop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uz.alishev.bookShop.dao.PersonDAO;
import uz.alishev.bookShop.model.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDao) {
        this.personDAO = personDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(personDAO.show(person.getName()).isPresent())
            errors.rejectValue("name","","This name is already taken");
    }
}
