package com.example;

import javax.servlet.annotation.WebServlet;

import com.example.view.logged.MainPage;
import com.example.view.unlogged.LoginPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import sun.applet.Main;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.example.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        new Navigator(this,this);
        getNavigator().addView(LoginPage.PAGE_NAME, LoginPage.class);
        getNavigator().addView(MainPage.PAGE_NAME, MainPage.class);
        getNavigator().navigateTo(LoginPage.PAGE_NAME);

        if(getSession().getAttribute("username")!=null){
            getNavigator().navigateTo(MainPage.PAGE_NAME);
            System.out.println(getSession().getAttribute("username"));
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
