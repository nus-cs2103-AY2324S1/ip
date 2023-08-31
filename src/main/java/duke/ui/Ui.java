package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static final String HORIZONTAL_LINE = "        ____________________________________________________________\n";
    public static final String INDENT = "        ";

    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE
                + INDENT + "Hello! I'm Glenda!\n"
                + INDENT + "What can I do for you?\n"
                + HORIZONTAL_LINE);
    }

    public void showGoodbyeMessage() {
        System.out.println(HORIZONTAL_LINE
                + INDENT + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }

    public void showAddedTask(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Got it. I've added this task to the list:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            // Case where there is no tasks to be displayed
            System.out.println(INDENT + "No tasks added. ");
        } else {
            System.out.println(INDENT + "Here are the task(s) in your list:");

            for (Task task : tasks) {
                System.out.println(INDENT + (tasks.indexOf(task) + 1) + ". " + task);
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Great! I've completed this task!");
        task.markAsDone();
        System.out.println(INDENT + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTaskMarkedAsUndone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I have not yet completed this task:");
        task.markAsUndone();
        System.out.println(INDENT + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showDeletedTask(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I've removed this task:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(HORIZONTAL_LINE
                + INDENT + errorMessage + "\n"
                + HORIZONTAL_LINE);
    }
}

