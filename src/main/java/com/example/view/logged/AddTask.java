package com.example.view.logged;

import com.example.Domain.Task;
import com.example.FieldsFactory.FieldsFactory;
import com.example.FieldsFactory.TaskFieldFactory;
import com.example.Service.TasksService;
import com.example.Validators.PasswordValidator;
import com.example.view.unlogged.CreateNewAccount;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Maciek on 2015-03-24.
 */
public class AddTask extends CustomComponent implements View {
    public static final String PAGE_NAME = "newTask";

    private final TextField taskName;
    private final TextArea taskDescription;
    final Embedded image = new Embedded("Uploaded Image");
    private String uploadedFileName = "";

    Upload.SucceededListener listener;
    ImageUploader receiver = new ImageUploader();

    public AddTask(){
        TaskFieldFactory taskFieldFactory = new TaskFieldFactory();
        taskName = (TextField) taskFieldFactory.getTaskNameField();
        taskDescription = (TextArea) taskFieldFactory.getTaskDescription();

        Upload upload = new Upload("Upload image", receiver);
        upload.addSucceededListener(receiver);
        setSizeFull();

        Button createTask = new Button("Create task");
        createTask.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                int UserId = (int) VaadinSession.getCurrent().getAttribute("userId");

                Task task = new Task(1,UserId, taskName.getValue(), taskDescription.getValue(), uploadedFileName);
                TasksService.addTask(task);
                getUI().getNavigator().navigateTo(MainPage.PAGE_NAME);
            }
        });
        VerticalLayout fields = new VerticalLayout(taskName, taskDescription,createTask,upload);
        fields.addComponent(image);
        image.setHeight("300px");
        image.setWidth("300px");
        image.setVisible(false);

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


    class ImageUploader implements Upload.Receiver, Upload.SucceededListener {
        public File file;

        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            // Create upload stream
            FileOutputStream fos = null; // Stream to write to
            try {
                // Open the file for writing.

                String workingDir = System.getProperty("user.dir");

                System.out.println(workingDir+"\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\img\\"+filename);
                file = new File(workingDir+"\\src\\main\\webapp\\VAADIN\\themes\\mytheme\\img\\"+filename);
                fos = new FileOutputStream(file);
                uploadedFileName = filename;
            } catch (final java.io.FileNotFoundException e) {
                new Notification("Could not open file<br/>",
                        e.getMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
                return null;
            }
            return fos; // Return the output stream to write to
        }

        public void uploadSucceeded(Upload.SucceededEvent event) {
            // Show the uploaded file in the image viewer
            System.out.println("ASDASDASDS");
            image.setVisible(true);
            image.setSource(new FileResource(file));
        }
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("Ładuje strone");
    }
}
