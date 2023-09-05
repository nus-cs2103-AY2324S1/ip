package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the object that displays output from the chatbot.
 */
public class UI {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Display greetings to user.
     */
    public void greet() {
        System.out.println("____________________________________________________________");
        String logo = "  __ _  ___ ___  _   _  _   _  \n"
                + " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n"
                + "( (( o )   /| || o || \\\\ || o |\n"
                + " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays bye to user.
     */
    public void exit() {
        scanner.close();
        System.out.println("Bye");
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the next line of user input.
     * @return The next line of user input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Returns whether there is still input.
     * @return Whether there is still user input
     */
    public boolean hasInput() {
        return scanner.hasNextLine();
    }

    /**
     * Displays the current tasks list.
     * @param list The current tasks list
     */
    public void displayList(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks in the list");
        }
        System.out.println(list);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays what task is added to the list.
     * @param task The task to be added
     * @param totalSize The total size of the list
     */
    public void displayAddToList(Task task, int totalSize) {
        System.out.println("This task is added to the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list%n", totalSize);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays what task is removed from the list.
     * @param task The task to be removed
     * @param totalSize The total size of the list
     */
    public void displayRemoveFromList(Task task, int totalSize) {
        System.out.println("This task is deleted from the list");
        System.out.println(task);
        System.out.printf("You now have %d tasks in your list:%n", totalSize);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays what task is marked as done.
     * @param task The task to be marked as done
     */
    public void displayDoneTask(Task task) {
        System.out.println("This task is marked as done");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays what task is marked as not done.
     * @param task The task to be marked as not done
     */
    public void displayNotDoneTask(Task task) {
        System.out.println("This task is marked as not done");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a specified list of tasks.
     * @param tasks The string representing the specified list of tasks
     */
    public void displayTasks(String tasks) {
        System.out.println(tasks);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the DukeException
     * @param exception The DukeException to be displayed
     */
    public void displayException(DukeException exception) {
        System.out.println(exception.getMessage());
        System.out.println("____________________________________________________________");
    }
}
