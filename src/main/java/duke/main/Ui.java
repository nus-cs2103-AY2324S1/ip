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
        return "Commands: \n" +
                "list: Lists existing tasks\n" +
                "find {string}: Lists tasks that contains string\n" +
                "mark {index}: Marks task as completed at index\n" +
                "unmark {index}: Unmarks task as incomplete at index\n" +
                "todo {task}: Adds a ToDo task \n" +
                "deadline {task} /by {date}: Adds a Deadline task\n" +
                "event {task} /from {date} /to {date}: Adds an Event task\n" +
                "tags {index}: Lists out tags of task\n" +
                "tag {index} {tag}: Adds a tag to task at index";
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
