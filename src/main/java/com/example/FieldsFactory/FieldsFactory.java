package com.example.FieldsFactory;

import com.example.Validators.PasswordValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.TextField;


/**
 * Created by Maciek on 2015-03-21.
 */
public class FieldsFactory extends CustomComponent  {
    public final Field getPasswordField() {
        PasswordField passwordField;
        passwordField = new PasswordField("Password: ");
        passwordField.setWidth("300px");
        passwordField.setRequired(true);
        passwordField.setSizeFull();
        passwordField.addValidator(new PasswordValidator());
        return passwordField;
    }
    public final Field getUserNameField() {
        TextField usernameField;
        usernameField = new TextField("Username: ");
        usernameField.setWidth("300px");
        usernameField.setRequired(true);
        usernameField.setInputPrompt("Your username, e.g. Mike123");
        usernameField.setInvalidAllowed(false);
        return usernameField;
    }
    public final Field getNameField() {
        TextField nameField;
        nameField = new TextField("Name: ");
        nameField.setWidth("300px");
        nameField.setRequired(true);
        nameField.setInputPrompt("Your name, e.g. Mike");
        nameField.setInvalidAllowed(false);
        return nameField;
    }
    public final Field getLastNameField() {
        TextField getLastNameField;
        getLastNameField = new TextField("Last name: ");
        getLastNameField.setWidth("300px");
        getLastNameField.setRequired(true);
        getLastNameField.setInputPrompt("Your last name, e.g. Example");
        getLastNameField.setInvalidAllowed(false);
        return getLastNameField;
    }

}
