package com.example.Domain;

/**
 * Created by Maciek on 2015-03-22.
 */
public class Task {
    public int id;
    public int ownerId;
    public String name;
    public String description;
    public String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Task(int id, int ownerId, String name, String description, String fileName) {

        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Task() {

    }


    public int getId() {
        return id;
    }

    public Task(int id, int ownerId, String name, String description) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
    }
}
