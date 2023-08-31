package duke;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user through the console.
 * It provides methods for reading user input, greeting the user, and displaying information.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println("Hello! I'm Bot");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param list The TaskList containing the tasks to be listed.
     */
    public void listTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
