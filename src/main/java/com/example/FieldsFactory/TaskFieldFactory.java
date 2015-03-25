package com.example.FieldsFactory;

import com.example.Validators.PasswordValidator;
import com.vaadin.ui.*;

/**
 * Created by Maciek on 2015-03-24.
 */
public class TaskFieldFactory extends CustomComponent {
    public final Field getTaskNameField() {
        TextField textField;
        textField = new TextField("Task name: ");
        textField.setWidth("300px");
        textField.setRequired(true);
        textField.setSizeFull();
        return textField;
    }
    public final Field getTaskDescription() {
        TextArea textField;
        textField = new TextArea("Task description: ");
        textField.setWidth("300px");
        textField.setRequired(true);
        textField.setSizeFull();
        return textField;
    }
}
