package duke.main;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initializes Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n");
        showLine();
    }

    /**
     * Prints out error message with string wrapper.
     *
     * @param error Error message to be printed.
     */
    public void showError(String error) {
        System.err.println("     " + error);
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println("   ----------------------");
    }

    /**
     * Prints out loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file, creating an empty list.");
    }


    /**
     * Prints out list of commands.
     */
    public String showCommands() {
        return "[LIST TASKS] \n"
                + "list: Lists tasks and shows {INDEX} of each task\n"
                + "find {STRING}: Lists tasks that contains string\n\n"
                + "[ADDING TASKS]\n"
                + "todo {TASK}\n"
                + "deadline {TASK} /by {DATE}\n"
                + "event {TASK} /from {DATE} /to {DATE}\n\n"
                + "{DATE} format: yyyy-mm-dd\n\n"
                + "[MARKING TASKS]\n"
                + "mark {INDEX}: Marks task as [X]\n"
                + "unmark {INDEX}: Unmarks task as [  ]\n\n"
                + "[TAGS]\n"
                + "tags {TASK}: Lists out tags of task\n"
                + "tag {INDEX} {TAG}: Adds a tag to task at index";
    }

    /**
     * Reads command from user input.
     *
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
