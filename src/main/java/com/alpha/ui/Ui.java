package com.alpha.ui;

import java.util.List;
import java.util.Scanner;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Ui.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    public void welcome() {
        lineBreak();
        System.out.println("Hello! I'm Alpha.");
        System.out.println("What can I do for you?");
        lineBreak();
    }

    /**
     * Prints the goodbye message.
     */
    public void goodbye() {
        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }

    /**
     * Proccess the next input from the user.
     *
     * @return Input string from user.
     */
    public String proccessInput() {
        return sc.nextLine();
    }

    /**
     * Display all tasks given a list of tasks.
     *
     * @param tasks List of tasks.
     */
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

    /**
     * Display message when adding a task.
     *
     * @param task     Task to be added.
     * @param taskList Task list of the application.
     */
    public void addTask(Task task, TaskList taskList) {
        lineBreak();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        lineBreak();
    }

    /**
     * Display message when marking task as done.
     *
     * @param task Task to be marked.
     */
    public void markTask(Task task) {
        lineBreak();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        lineBreak();
    }

    /**
     * Display message when marking task as not done.
     *
     * @param task Task to be unmarked.
     */
    public void unmarkTask(Task task) {
        lineBreak();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        lineBreak();
    }

    /**
     * Display message deleting task.
     *
     * @param task     Task to be deleted.
     * @param taskList Task list of the application.
     */
    public void deleteTask(Task task, TaskList taskList) {
        lineBreak();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        lineBreak();
    }

    /**
     * Display loading error.
     */
    public void loadingError() {
        System.out.println("Error loading. Please try again.");
    }

    /**
     * Display line break.
     */
    public void lineBreak() {
        System.out.println("______________________________");
    }
}
