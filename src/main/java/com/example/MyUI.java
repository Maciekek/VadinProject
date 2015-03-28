package com.example;

import javax.servlet.annotation.WebServlet;

import com.example.Service.PersonManager;
import com.example.view.logged.AddTask;
import com.example.view.logged.MainPage;
import com.example.view.logged.ShowTask;
import com.example.view.unlogged.CreateNewAccount;
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
    public String PAGE_NAME = "my";
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        new Navigator(this,this);
        getNavigator().addView(MainPage.PAGE_NAME, MainPage.class);
        getNavigator().addView(LoginPage.PAGE_NAME, LoginPage.class);
        getNavigator().addView(CreateNewAccount.PAGE_NAME, CreateNewAccount.class);
        getNavigator().addView(MainPage.PAGE_NAME,MainPage.class);
        getNavigator().addView(AddTask.PAGE_NAME, AddTask.class);
        getNavigator().addView(ShowTask.PAGE_NAME, ShowTask.class);

        if(getSession().getAttribute("userName")!=null){
            getNavigator().navigateTo(MainPage.PAGE_NAME);
        }else{
            getNavigator().navigateTo(LoginPage.PAGE_NAME);
        }



    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
