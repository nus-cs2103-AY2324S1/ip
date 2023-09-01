package ui;

import exceptions.BocchiException;
import task.Task;
import task.TaskList;

public class Ui {
    private static final String LINE_BREAK = "___________________________________________________";

    public Ui() {
    }

    /**
     * Outputs greeting message.
     */
    public void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Bocchi");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs exit message.
     */
    public void exit() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs successful loading of data message.
     */
    public void loadSuccessful() {
        System.out.println("Previous data has been loaded");
    }

    /**
     * Outputs unsuccessful loading of data message.
     */
    public void loadUnsuccessful() {
        System.out.println("No previous data found");
    }

    /**
     * Outputs successful addition of task message.
     */
    public void addTaskSuccessful(Task task, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs successful deletion of task message.
     */
    public void deleteTaskSuccessful(Task task, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs successful marking of task message.
     */
    public void markTaskSuccessful(int taskNumber, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.getTask(taskNumber));
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs successful unmarking of task message.
     */
    public void unmarkTaskSuccessful(int taskNumber, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.getTask(taskNumber));
        System.out.println(LINE_BREAK);
    }

    public void displayTasks(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println(taskList);
        System.out.println(LINE_BREAK);
    }

    public void exception(BocchiException e) {
        System.out.println(LINE_BREAK);
        System.out.println(e.getMessage());
        System.out.println(LINE_BREAK);
    }
}
