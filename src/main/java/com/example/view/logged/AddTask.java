package com.example.view.logged;

import com.example.Domain.Task;
import com.example.FieldsFactory.FieldsFactory;
import com.example.FieldsFactory.TaskFieldFactory;
import com.example.Service.TasksService;
import com.example.Validators.PasswordValidator;
import com.example.view.unlogged.CreateNewAccount;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Maciek on 2015-03-24.
 */
public class AddTask extends CustomComponent implements View {
    public static final String PAGE_NAME = "newTask";

    private final TextField taskName;
    private final TextArea taskDescription;

    public AddTask(){
        TaskFieldFactory taskFieldFactory = new TaskFieldFactory();
        taskName = (TextField) taskFieldFactory.getTaskNameField();
        taskDescription = (TextArea) taskFieldFactory.getTaskDescription();

        setSizeFull();

        Button createTask = new Button("Create task");
        createTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                int UserId = (int) VaadinSession.getCurrent().getAttribute("userId");

                Task task = new Task(1,UserId, taskName.getValue(), taskDescription.getValue());
                TasksService.addTask(task);
                getUI().getNavigator().navigateTo(MainPage.PAGE_NAME);
            }
        });
        VerticalLayout fields = new VerticalLayout(taskName, taskDescription,createTask);



        fields.setCaption("Create new Task");
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
        System.out.println("Ładuje strone");
    }
}
