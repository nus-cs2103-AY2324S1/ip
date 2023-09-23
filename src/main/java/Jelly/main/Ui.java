package jelly.main;

import java.util.ArrayList;
import java.util.Scanner;

import jelly.task.Task;

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
    public String startUpMessage() {
        return ("Hello! I'm Jelly\n"
                + "What can I do for you?");
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
    public String displayErrorMessage(String message) {
        return (message);
    }

    /**
     * Displays the list of tasks in the storage.
     *
     * @param storage The tasklist that is in the storage.
     */
    public String printList(ArrayList<Task> storage) {
        String listOfTasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < storage.size(); i++) {
            listOfTasks = listOfTasks.concat((i + 1) + "." + storage.get(i).toString() + "\n");
        }
        return listOfTasks;
    }

    /**
     * Displays a completion message after successfully adding a task to the list.
     *
     * @param addedTask The task that was added.
     * @param noOfTasks The total number of tasks in the list after adding.
     */
    public String addedTaskMessage(Task addedTask, int noOfTasks) {
        return ("Ok! I've added this task: \n" + addedTask.toString() + "\n"
                + "Now you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Displays a completion message after successfully deleting a task from the list.
     *
     * @param deletedTask The task that was deleted.
     * @param noOfTasks The total number of tasks in the list after deletion.
     */
    public String deleteMessage(Task deletedTask, int noOfTasks) {
        return ("Okay, I've removed this task: \n" + deletedTask + "\n"
                + "Now you have " + noOfTasks + " in the list.");
    }

    /**
     * Displays a final message to the user before the Chat Bot shuts down.
     */
    public String byeMessage() {
        return ("Bye mate! Have a nice day :]");
    }
}
