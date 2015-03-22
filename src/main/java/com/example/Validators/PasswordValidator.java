package com.example.Validators;

import com.vaadin.data.validator.AbstractValidator;

/**
 * Created by Maciek on 2015-03-22.
 */
public class PasswordValidator extends AbstractValidator<String> {
    public PasswordValidator() {
        super("The password is not valid");
    }

    @Override
    protected boolean isValidValue(String passwordValue) {
        if(passwordValue !=null && !passwordValue.equals("") && passwordValue.length() < 4){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Class getType() {
        return String.class;
    }
}
