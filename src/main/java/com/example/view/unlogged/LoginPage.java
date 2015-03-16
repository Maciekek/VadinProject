package com.example.view.unlogged;

import com.example.view.logged.MainPage;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import sun.rmi.runtime.Log;

/**
 * Created by Maciek on 2015-03-15.
 */
public class LoginPage extends CustomComponent implements View,Button.ClickListener {
    private final TextField username;
    private final PasswordField password;

    public static final String PAGE_NAME = "loginPage";

    public LoginPage(){
        setSizeFull();
        username = new TextField("Username: ");
        username.setWidth("300px");
        username.setRequired(true);
        username.setInputPrompt("Your username");
        username.setInvalidAllowed(false);

        password = new PasswordField("Password: ");
        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        Button loginButton = new Button("Login", this);

        VerticalLayout fields = new VerticalLayout(username, password, loginButton);
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
        if(!username.isValid() || !password.isValid()){
            return;
        }

        if(username.getValue().equals("test") & password.getValue().equals("test")){
            getSession().setAttribute("username", username.getValue());
            System.out.println("Logged correctry");
            getUI().getNavigator().navigateTo(MainPage.PAGE_NAME);

        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
