package duke.main;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;

/**
 * The UI class is responsible for displaying messages and interacting with the user interface.
 * It provides methods to print messages, display lists of tasks, and handle user interactions.
 */
public class UI {
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets the user with a logo and a personalized message.
     *
     * @param chatBotName The name of the chatbot.
     */
    public void greetUser(String chatBotName) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm " + chatBotName + "! SUIIII!!!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the list of tasks.
     *
     * @param list The list of tasks to display.
     * @param numOfTasks The number of tasks in the list.
     */
    public void displayList(ArrayList<Task> list, int numOfTasks) {
        printLine();
        System.out.println("Here are the duke.tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        printLine();
    }

    /**
     * Displays an error message from a DukeException.
     *
     * @param e The DukeException containing the error message.
     */

    public void displayError(DukeException e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }

    /**
     * Displays a goodbye message.
     */
    public void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Displays a message after adding a task.
     *
     * @param taskName The name of the added task.
     * @param numOfTasks The updated number of tasks in the list.
     */
    public void addTask(String taskName, int numOfTasks) {
        printLine();
        System.out.println("Got it. I've added the task:\n" + taskName);
        if (numOfTasks != 1) {
            System.out.println("Now you have " + numOfTasks + " duke.tasks in your list, just like how I have 5 Ballon d'Ors.");
        } else {
            System.out.println("Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.");
        }
        printLine();
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param taskName The name of the deleted task.
     * @param numOfTasks The updated number of tasks in the list.
     */
    public void deleteTask(String taskName, int numOfTasks) {
        printLine();
        System.out.println("Removed task:\n" + taskName);
        System.out.println("Now you have " + numOfTasks + " duke.tasks in your list.");
        printLine();
    }


    /**
     * Displays a message after marking a task as done.
     *
     * @param taskName The name of the marked task.
     */
    public void markTask(String taskName) {
        printLine();
        System.out.println("SIUUU! I've marked this task as done. We will make Saudi League number 1.\n [X] " + taskName);
        printLine();
    }

    /**
     * Displays a message after marking a task as not done.
     *
     * @param taskName The name of the unmarked task.
     */
    public void unMarkTask(String taskName) {
        printLine();
        System.out.println("OK, I've marked this task as not done.\n [ ] " + taskName);
        printLine();
    }
}
