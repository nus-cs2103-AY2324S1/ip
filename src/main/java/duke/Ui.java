package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interaction and displaying messages.
 */
public class Ui {
    private Scanner sc;
<<<<<<< HEAD
    String horizontalLine = "____________________________________________________________";

    /**
     * Constructs a Ui object with a scanner to read user input.
     */
=======

>>>>>>> branch-A-CodingStandard
    public Ui() {
        this.sc = new Scanner(System.in);
    }

<<<<<<< HEAD
    /**
     * Displays a welcome message when the application starts.
     */
=======
    String horizontalLine = "____________________________________________________________";

>>>>>>> branch-A-CodingStandard
    public void showWelcomeMessage() {
        String name = "duke.Tired";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine + "\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a goodbye message when the application exits.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

<<<<<<< HEAD
    // for future use
=======
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    // Will use in future
>>>>>>> branch-Level-9
    public void showAddedTask(Task task) {
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(horizontalLine);
    }

    public void showMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showMarkedAsUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showRemovedTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }
}