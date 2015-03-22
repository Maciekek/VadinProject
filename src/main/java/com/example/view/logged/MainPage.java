package com.example.view.logged;

import com.example.Domain.Task;
import com.example.view.unlogged.LoginPage;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Maciek on 2015-03-16.
 */
public class MainPage extends CustomComponent implements View, Button.ClickListener{
    public static final String PAGE_NAME = "MainPage";

    private Task task = new Task("asd","qwe");
    private BeanItemContainer<Task> tasks = new BeanItemContainer<Task>(Task.class);

    public MainPage(){
        Button loginButton = new Button("Logout", this);
        VerticalLayout fields = new VerticalLayout(loginButton);
        fields.setCaption("Welcome on Main Page: " + VaadinSession.getCurrent().getAttribute("userName"));
        System.out.println(VaadinSession.getCurrent().getAttribute("userName"));

        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();


        final Table table = new Table("Tasks", tasks);
        table.setSizeFull();
        table.setColumnHeader("name", "Name");
        table.setColumnHeader("description", "Description");
        table.setSelectable(true);
        table.setImmediate(true);
        table.addItem(task);


        Button addTask = new Button("Add task");
        addTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                

            }
        });

        VerticalLayout viewLayout = new VerticalLayout(fields,table,addTask);
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
        getUI().getSession().close();
        getUI().getNavigator().navigateTo(LoginPage.PAGE_NAME);
    }
}
