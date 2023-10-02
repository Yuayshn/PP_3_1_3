package ru.javamentor.springmvc.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javamentor.springmvc.model.User;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

    }
}
