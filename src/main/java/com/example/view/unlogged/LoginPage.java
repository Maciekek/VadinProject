package com.example.view.unlogged;

import com.example.Domain.Person;
import com.example.FieldsFactory.FieldsFactory;
import com.example.Service.PersonManager;
import com.example.Validators.PasswordValidator;
import com.example.view.logged.MainPage;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Maciek on 2015-03-15.
 */
public class LoginPage extends CustomComponent implements View,Button.ClickListener {
    private final TextField username;
    private final PasswordField passwordField;

    public static final String PAGE_NAME = "loginPage";

    private PersonManager personManager = new PersonManager();

    public LoginPage(){
        FieldsFactory fieldsFactory= new FieldsFactory();
        passwordField = (PasswordField) fieldsFactory.getPasswordField();
        username = (TextField) fieldsFactory.getUserNameField();
        passwordField.addValidator(new PasswordValidator());
        setSizeFull();

        Button loginButton = new Button("Login", this);
        Button createNewAccout = new Button("Create new account", this);

        createNewAccout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo(CreateNewAccount.PAGE_NAME);
            }
        });

        VerticalLayout fields = new VerticalLayout(username, passwordField, loginButton, createNewAccout);

        fields.setCaption("Please login");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        System.out.println("Click on login button");
        if(!username.isValid() || !passwordField.isValid()){
            System.out.println("asd");
            return;
        }
        System.out.println(personManager.getPersons().isEmpty());
        for(Person p: personManager.getPersons() ){
            if(p.getName().equals(username.getValue())){
                if(passwordIsCorrect(p.getPassword(), passwordField.getValue())){
                    VaadinSession.getCurrent().setAttribute("userName", username.getValue());
                    VaadinSession.getCurrent().setAttribute("userId", PersonManager.getUserIdByUserName(username.getValue()));
                    System.out.println("USERID: " + PersonManager.getUserIdByUserName(username.getValue()));
                    System.out.println(getSession().getAttribute("userName"));

                    getUI().getNavigator().navigateTo(MainPage.PAGE_NAME);
                }
            }
        }

    }

    private boolean passwordIsCorrect(String password, String typePassword) {
        if(password.equals(typePassword)){
            return true;
        }
        return false;

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
