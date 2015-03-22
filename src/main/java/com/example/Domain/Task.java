package com.example.Domain;

/**
 * Created by Maciek on 2015-03-22.
 */
public class Task {
    public String name;
    public String description;

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

    public Task() {

    }

    public Task(String name, String description) {

        this.name = name;
        this.description = description;
    }
}
