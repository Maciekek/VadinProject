package com.example.view.unlogged;

import com.example.Domain.Person;
import com.example.FieldsFactory.FieldsFactory;
import com.example.Service.PersonManager;
import com.example.Validators.PasswordValidator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;




/**
 * Created by Maciek on 2015-03-17.
 */
public class CreateNewAccount extends CustomComponent implements View {
    public static final String PAGE_NAME = "createNewAccoutn";

    private TextField nameField;
    private TextField userNameField;
    private TextField lastNameField;
    private PasswordField passwordField;
    private PersonManager personManager = new PersonManager();

    private Person person = new Person();
    private BeanItem<Person> personBeanItem = new BeanItem<Person>(person);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        FieldsFactory fieldsFactory = new FieldsFactory();
        final FieldGroup binder = new FieldGroup(personBeanItem);

        nameField = (TextField) fieldsFactory.getNameField();
        userNameField = (TextField) fieldsFactory.getUserNameField();
        lastNameField = (TextField) fieldsFactory.getLastNameField();
        passwordField = (PasswordField) fieldsFactory.getPasswordField();
        binder.bind(nameField, "name");
        binder.bind(userNameField,"userName");
        binder.bind(lastNameField,"lastName");
        binder.bind(passwordField, "password");

        final Label l = new Label("Username is already used");
        l.setVisible(false);

        binder.getField("name").setRequired(true);
        binder.getField("lastName").setRequired(true);

        binder.setBuffered(true);

        Button addPerson = new Button("Create account");
        addPerson.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    if (!accountIsExsisting(binder)) {
                        binder.commit();
                        personManager.addPerson(person);
                        getUI().getNavigator().navigateTo(LoginPage.PAGE_NAME);
                    }else{
                        l.setVisible(true);
                    }
                } catch (FieldGroup.CommitException e) {
                    Notification.show("Can not create new account","Some of fields has wrong value", Notification.Type.TRAY_NOTIFICATION);
                    if (binder.getField("userName").isEmpty())
                        binder.getField("userName").setRequiredError("Invalid value");
                    if (binder.getField("name").isEmpty())
                        binder.getField("name").setRequiredError("Invalid value");
                    if (binder.getField("lastName").isEmpty())
                        binder.getField("lastName").setRequiredError("Invalid value");
                    if (binder.getField("password").isEmpty())
                        binder.getField("password").setRequiredError("Invalid value");
                }
            }

            private boolean accountIsExsisting(FieldGroup binder) {
                for (Person p : personManager.getPersons()) {
                    if (p.getUserName().equals(binder.getField("userName").getValue())) {
                        return true;
                    }
                }
                return false;
            }
        });

        addPerson.setSizeFull();
        VerticalLayout fields = new VerticalLayout(userNameField,nameField,lastNameField,passwordField,l,addPerson);
        fields.setCaption("Let`s create new account");
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }


}
