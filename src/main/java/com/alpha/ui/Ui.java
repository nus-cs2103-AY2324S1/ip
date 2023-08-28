package com.alpha.ui;

import java.util.List;
import java.util.Scanner;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    public void welcome() {
        lineBreak();
        System.out.println("Hello! I'm Alpha.");
        System.out.println("What can I do for you?");
        lineBreak();
    }

    public void goodbye() {
        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }

    public String proccessInput() {
        return sc.nextLine();
    }

    public void displayTasks(List<Task> tasks) {
        lineBreak();
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            String row = count++ + "." + task.toString();
            System.out.println(row);
        }
        lineBreak();
    }

    public void addTask(Task task, TaskList taskList) {
        lineBreak();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        lineBreak();
    }

    public void markTask(Task task) {
        lineBreak();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        lineBreak();
    }

    public void unmarkTask(Task task) {
        lineBreak();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        lineBreak();
    }

    public void deleteTask(Task task, TaskList taskList) {
        lineBreak();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        lineBreak();
    }

    public void loadingError() {
        System.out.println("Error loading. Please try again.");
    }

    public void lineBreak() {
        System.out.println("______________________________");
    }
}
