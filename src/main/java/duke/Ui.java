package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the part of the chatbot that deals with interactions with users.
 */
public class Ui {
    /** Scanner object to scan user's input. */
    private Scanner sc;
    /** Constant to represent the partition between commands and outputs. */
    private static String partition = "--------------------------------------";

    /**
     * Constructor to create an Ui object.
     * Starts the chatbot.
     */
    public Ui() {
        sc = new Scanner(System.in);

        System.out.println(partition + "\n" + "Hello! I'm Rion");
        System.out.println("What can I do for you?\n" + partition);
    }

    /**
     * Represents the output after successfully adding a task to the list.
     * 
     * @param task The task that got added into the task list.
     * @param size The changed size of the task list.
     */
    public void addToListSuccess(Task task, int size) {
        System.out.println(partition + "\nadded:\n" + task + "\n" +
        "You have " + size + " tasks in the list.\n" + partition);
    }

    /**
     * Represents the output after successfully deleting a task from the list.
     * 
     * @param task The task that got deleted.
     * @param size The changed size of the task list.
     */
    public void deleteFromListSuccess(Task task, int size) {
        System.out.println(partition + "\nOK, I've deleted the task:\n" 
        + task + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Represents the output that outputs the list stored.
     * 
     * @param taskList Task list saved within the chatbot.
     */
    public void printList(ArrayList<Task> taskList) {
        System.out.println(partition + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + taskList.get(i));
        }
        System.out.println(partition);
    }

    /**
     * Represents the output that is printed when exiting the chatbot.
     */
    public void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    /**
     * Represents the output after an error has occurred.
     * 
     * @param message The error message.
     */
    public void printError(String message) {
        printMessage(message);
    }

    /**
     * Represents the output after an error occurred during file loading.
     * 
     * @param error The error message.
     */
    public void showLoadingError(String error) {
        printMessage("OOPS! An error occurred during file loading " + error);
    }

    /**
     * Represents the output for every command.
     * 
     * @param message The command message.
     */
    public void printMessage(String message) {
        System.out.println(partition + "\n" + message + "\n" + partition);
    }

    /**
     * Gets the next command inputted.
     * 
     * @return The next command.
     */
    public String nextCommand() {
        return sc.nextLine();
    }
}
