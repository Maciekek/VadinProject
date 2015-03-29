package com.example.Service;

import com.example.Domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Maciek on 2015-03-22.
 */
public class TasksService {

    private static List<Task> tasks = new ArrayList<Task>();

    public TasksService() {

    }

    public static void addTask(Task task){
        System.out.println("DOdaje taks: " + task);
        System.out.println("ADDTASK 01: " + tasks);
        tasks.add(task);
        System.out.println("ADDTASK 02: " + tasks);
    }

    public static List<Task> getTasks(int userId) {
        System.out.println("USERID: " + userId);
        List<Task> myTasks = new ArrayList<Task>();
        System.out.println("TASK SIZE 01: " + tasks.size());
        for(Task task : tasks){

            System.out.println();

            if(userId == task.getOwnerId()){
                myTasks.add(task);
            }
        }
        return myTasks;
    }

    public static void deleteTasks(Object tasksToRemove) {
            tasks.remove((Task) tasksToRemove);
    }
}
