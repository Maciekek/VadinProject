package com.example.view.logged;

import com.example.Domain.Task;
import com.example.FieldsFactory.FieldsFactory;
import com.example.FieldsFactory.TaskFieldFactory;
import com.example.Validators.PasswordValidator;
import com.example.view.unlogged.CreateNewAccount;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by Maciek on 2015-03-28.
 */
public class ShowTask extends CustomComponent implements View {
    public static final String PAGE_NAME = "ShowTask";
    public static TextField taskName;
    public static TextArea taskDesc;
    public ShowTask(){
        Task task = (Task) VaadinSession.getCurrent().getAttribute("obj");

        TaskFieldFactory taskFieldFactory = new TaskFieldFactory();
        taskName = (TextField) taskFieldFactory.getTaskNameField();
        taskDesc = (TextArea) taskFieldFactory.getTaskDescription();
        setSizeFull();

        taskName.setValue(task.getName());
        taskDesc.setValue(task.getDescription());
        taskName.setWidth("300px");
        taskDesc.setWidth("300px");
        taskDesc.setEnabled(false);
        taskName.setEnabled(false);
        Image image = new Image(null,
                new ThemeResource("img/" + task.getFileName()));

        image.setSizeUndefined();

        Panel panel = new Panel("Image");

        panel.setContent(image);


        Button saveChanges = new Button("Save");
        saveChanges.setVisible(false);
        saveChanges.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                task.setName(taskName.getValue());
                task.setDescription(taskName.getValue());
            }
        });

        Button updateTask = new Button("Let`s update task");
        updateTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                taskName.setEnabled(true);
                taskDesc.setEnabled(true);
                saveChanges.setVisible(true);
            }
        });

        VerticalLayout fields = new VerticalLayout(taskName,taskDesc,panel,saveChanges,updateTask);


        fields.setCaption("Your task");
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
}
