package com.example.view.logged;

import com.example.view.unlogged.LoginPage;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Maciek on 2015-03-16.
 */
public class MainPage extends CustomComponent implements View, Button.ClickListener{
    public static final String PAGE_NAME = "MainPage";
    public MainPage(){
        Button loginButton = new Button("Logout", this);
        VerticalLayout fields = new VerticalLayout(loginButton);
        fields.setCaption("Welcome on Main Page: ");
//        System.out.println(getSession().getAttribute("username"));
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
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        getSession().close();
        getUI().getNavigator().navigateTo(LoginPage.PAGE_NAME);
    }
}
