package com.example.view.logged;

import com.example.Domain.Person;
import com.example.Domain.Task;
import com.example.Service.TasksService;
import com.example.view.unlogged.LoginPage;
import com.sun.jmx.snmp.tasks.TaskServer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ImageRenderer;
import com.vaadin.ui.themes.Reindeer;

import java.util.Collection;
import java.util.Locale;

/**
 * Created by Maciek on 2015-03-16.
 */
public class MainPage extends CustomComponent implements View, Button.ClickListener{
    public static final String PAGE_NAME = "MainPage";
    private TasksService tasksService = new TasksService();
    private BeanItemContainer<Task> tasks = new BeanItemContainer<Task>(Task.class);

    private Grid grid;
    public MainPage(){
        VerticalLayout fields = new VerticalLayout();
        Button loginButton = new Button("Logout", this);

        fields.setCaption("Welcome on Main Page: " + VaadinSession.getCurrent().getAttribute("userName"));
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        grid = new Grid(tasks);
        grid.setWidth("500px");



        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        TextField nameEditor = new TextField();
        grid.setEditorEnabled(true);

        grid.setCellStyleGenerator(new Grid.CellStyleGenerator() {
            public String getStyle(com.vaadin.ui.Grid.CellReference cellReference) {
                return cellReference.getPropertyId().equals("fileName") ? "imagecol" : null;}
        });
//        grid.getColumn("name").setEditorField(nameEditor);

        Button deleteSelected = new Button("Delete Selected", e -> {
            // Delete all selected data items
            for (Object itemId: grid.getSelectedRows())
                grid.getContainerDataSource().removeItem(itemId);

            // Disable after deleting
            e.getButton().setEnabled(false);
            Notification.show(grid.getSelectedRows().size() + " removed.", Notification.Type.TRAY_NOTIFICATION);
        });
        deleteSelected.setEnabled(false);

//        grid.addItemClickListener(new ItemClickEvent.ItemClickListener() {
//            @Override
//            public void itemClick(ItemClickEvent itemClickEvent) {
//                VaadinSession.getCurrent().setAttribute("obj", itemClickEvent.getItemId());
//                getUI().getNavigator().navigateTo(ShowTask.PAGE_NAME);
//            }
//        });

        grid.addSelectionListener(selection -> {
            deleteSelected.setEnabled(true);

            TasksService.deleteTasks(selection.getSelected().toArray());
            System.out.println(selection.getSelected().toString());

        });

        Button addTask = new Button("Add task");
        addTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo(AddTask.PAGE_NAME);

            }
        });

        fields.addComponent(grid);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(addTask);
        horizontalLayout.addComponent(deleteSelected);
        horizontalLayout.setSpacing(true);
        fields.addComponent(horizontalLayout);
        fields.addComponent(loginButton);


        VerticalLayout viewLayout = new VerticalLayout(fields);
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
