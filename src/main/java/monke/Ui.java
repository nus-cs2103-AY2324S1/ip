package monke;

import monke.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interface interactions.
 * It handles reading user input and displaying output.
 */
public class Ui {
    /**
     * Prints a message to the console with indentation to show the bot is speaking.
     *
     * @param msg The message to be printed.
     */
    public void print(String msg) {
        System.out.println("\t" + msg);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        this.print("Hello, I'm Monke. OOGA BOOGA!");
        this.print("What can I do for you?");
        this.printHorizontalLine();
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String fullCommand = sc.nextLine();
            return fullCommand;
        }
        sc.close();
        return "";
    }

    /**
     * Prints a horizontal line to the console for visual separation.
     */
    public void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Displays list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void displayList(TaskList taskList) {
        List<Task> tasks = taskList.toList();
        for (int i = 0; i < tasks.size(); i++) {
            this.print((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Shows a message confirming the addition of a task to task list.
     *
     * @param task The task that was added.
     * @param tasksSize The total number of tasks in the list after the addition.
     */
    public void showAddTask(Task task, int tasksSize) {
        this.print("Got it. I've added this task:");
        this.print("\t" + task);
        this.print("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Prints exit message to the user.
     */
    public void printExit() {
        this.print("Bye. Hope to see you again soon! OOGA BOOGA!");
    }
}
