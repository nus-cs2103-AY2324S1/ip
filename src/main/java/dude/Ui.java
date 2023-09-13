package dude;

import java.util.Scanner;

import dude.task.Task;

/**
 * Represents the UI of Dude and deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints a welcome message for users.
     */
    public void showWelcome() {
        String greeting = "Hello, I'm Dude!\n"
                + "What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Prints a farewell message for users.
     */
    public void showFarewell() {
        String greeting = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Prints the list of tasks..
     */
    public void showTaskList(TaskList taskList) {
        int nTasks = taskList.getSize();
        if (nTasks == 0) {
            System.out.println("You have no saved tasks.");
        } else {
            for (int i = 0; i < nTasks; i++) {
                Task task = taskList.getTask(i);
                System.out.printf("%d. %s\n", i + 1, task.toString());
            }
        }
    }

    /**
     * Prints a statement to show the task that has been successfully marked.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf(task.toString());
        System.out.println();
        // task is already done?
    }

    /**
     * Prints a statement to show the task that has been successfully unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as as not done yet:");
        System.out.printf(task.toString());
        System.out.println();
        // task is already undone?
    }

    /**
     * Prints a statement to show the task that has been successfully deleted.
     */
    public void showDeletedTask(Task task, int nTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", nTasks);
    }

    /**
     * Prints a statement to show the task that has been successfully added.
     */
    public void showAddedTask(Task task, int nTasks) {
        System.out.printf("Got it. I've added this task:\n%s\n", task.toString());
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }

    /**
     * Prints a statement to show the command is unknown.
     */
    public void showUnknownCommand() {
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showError(String message) {
    }

    public void showLoadingError() {
    }
}
