package com.example.Service;

import com.example.Domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciek on 2015-03-22.
 */
public class TasksService {

    private static List<Task> tasks = new ArrayList<Task>();

    public TasksService() {

    }

    public static void addTask(Task task){
        System.out.println("DOdaje taks: " + task);
        System.out.println(tasks);
        tasks.add(task);
        System.out.println(tasks);
    }

    public static List<Task> getTasks(int userId) {
        System.out.println("USERID: " + userId);
        List<Task> myTasks = new ArrayList<Task>();
        for(Task task : tasks){

            System.out.println();
            if(userId == task.getOwnerId()){
                myTasks.add(task);
            }
        }
        return myTasks;
    }

    public static void removeTask(int id){
        System.out.println("Szukam taska");
        for(Task task : tasks){
            if(task.getId() == id){
                System.out.println("Task o id:  " + id + " został usunięty...");
                tasks.remove(task);
            }

        }
    }
}
