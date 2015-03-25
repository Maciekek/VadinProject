package com.example.view.logged;

import com.example.Domain.Person;
import com.example.Domain.Task;
import com.example.Service.TasksService;
import com.example.view.unlogged.LoginPage;
import com.sun.jmx.snmp.tasks.TaskServer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
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
    private TasksService tasksService = new TasksService();
    private BeanItemContainer<Task> tasks = new BeanItemContainer<Task>(Task.class);
    private final Table table = new Table("Tasks", tasks);
    public MainPage(){
        int userId = (int) VaadinSession.getCurrent().getAttribute("userId");
        Button loginButton = new Button("Logout", this);
        VerticalLayout fields = new VerticalLayout(loginButton);
        fields.setCaption("Welcome on Main Page: " + VaadinSession.getCurrent().getAttribute("userName"));

        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        table.setSizeFull();
        table.setColumnHeader("name", "Name");
        table.setColumnHeader("description", "Description");
        table.setSelectable(true);
        table.setImmediate(true);
        table.setContainerDataSource(tasks);
        tasks.addAll(TasksService.getTasks(userId));


        table.addGeneratedColumn("Delete", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table table, final Object o, final Object o1) {
                Button button = new Button("Delete");
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        Task task1 = (Task) o;
                        table.getContainerDataSource().removeItem(o);
                        TasksService.removeTask(task1.getId());
                    }
                });
                return button;
            }

        });
        table.addGeneratedColumn("Edit", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(final Table table, final Object o, Object o1) {
                Button button = new Button("Edit");

                button.addStyleName("Frienldy");
                button.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        table.getContainerDataSource().removeItem(o);
                    }
                });
                return button;
            }

        });



        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {

            }
        });
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Object rowId = table.getValue();
                System.out.println(table.getContainerProperty(rowId, "name"));
                System.out.println(itemClickEvent.getItemId());
            }
        });


        Button addTask = new Button("Add task");
        addTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo(AddTask.PAGE_NAME);

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
        tasks.addAll(tasksService.getTasks((int) VaadinSession.getCurrent().getAttribute("userId")));
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        getSession().close();
        getUI().getSession().close();
        getUI().getNavigator().navigateTo(LoginPage.PAGE_NAME);
    }
}
