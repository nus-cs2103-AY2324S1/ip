package Jelly.main;

import Jelly.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for the interface of the Jelly Chat Bot.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui, takes in user input from the keyboard.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out a welcome message when the Chat Bot is booted up.
     */
    public void startUpMessage() {
        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the command inputted by the user.
     *
     * @return A string of the command inputted.
     */
    public String commandMe() {
        return sc.nextLine();
    }

    /**
     * If there is an error, diplay it to the user.
     *
     * @param message The error message.
     */
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks in the storage.
     *
     * @param storage The tasklist that is in the storage.
     */
    public void printList(ArrayList<Task> storage) {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
    }

    /**
     * Displays a completion message after successfully adding a task to the list.
     *
     * @param addedTask The task that was added.
     * @param noOfTasks The total number of tasks in the list after adding.
     */
    public void addedTaskMessage(Task addedTask, int noOfTasks) {
        System.out.println("Ok! I've added this task: \n" + addedTask.toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Displays a completion message after successfully deleting a task from the list.
     *
     * @param deletedTask The task that was deleted.
     * @param noOfTasks The total number of tasks in the list after deletion.
     */
    public void deleteMessage(Task deletedTask, int noOfTasks) {
        System.out.println("Okay, I've removed this task: \n" + deletedTask);
        System.out.println("Now you have " + noOfTasks + " in the list.");
    }

    /**
     * Displays a final message to the user before the Chat Bot shuts down.
     */
    public void byeMessage() {
        System.out.println("Bye mate! Have a nice day :]");
    }
}
