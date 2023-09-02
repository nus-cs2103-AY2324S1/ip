package duke.lib;

import java.util.Scanner;

import duke.error.DukeException;


/**
 * Represents the user interface of the Duke application.
 */
public class UI {
    private Scanner scanner;

    /**
     * Constructs a UI object and initializes the scanner for user input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String logo =
                "    ,o888888o.    8 8888        8          .8.    8888888 8888888888 8888888 8888888888 `8.`8888.   "
                        + "   ,8'\n"
                        + "   8888     `88.  8 8888        8         .888.         8 8888             8 8888        `8"
                        + ".`8888.    ,8'\n"
                        + ",8 8888       `8. 8 8888        8        :88888.        8 8888             8 8888         `8"
                        + ".`8888.  ,8'\n"
                        + "88 8888           8 8888        8       . `88888.       8 8888             8 8888          "
                        + "`8.`8888.,8'\n"
                        + "88 8888           8 8888        8      .8. `88888.      8 8888             8 8888           "
                        + "`8.`88888'\n"
                        +
                        "88 8888           8 8888        8     .8`8. `88888.     8 8888             8 8888           "
                        + " `8. 8888\n"
                        + "88 8888           8 8888888888888    .8' `8. `88888.    8 8888             8 8888           "
                        + "  `8 8888\n"
                        + "`8 8888       .8' 8 8888        8   .8'   `8. `88888.   8 8888             8 8888           "
                        + "   8 8888\n"
                        + "   8888     ,88'  8 8888        8  .888888888. `88888.  8 8888             8 8888           "
                        + "   8 8888\n"
                        + "    `8888888P'    8 8888        8 .8'       `8. `88888. 8 8888             8 8888           "
                        + "   8 8888\n";


        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Oh.. bye");
    }

    /**
     * Displays an error message for loading data from the save file.
     */
    public void showLoadingError() {
        System.out.println("Error reading from save file.");
    }

    /**
     * Reads and returns a user command input.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("------------------------------------------");
    }

    /**
     * Displays an error message.
     *
     * @param error The DukeException containing the error message.
     */
    public void showError(DukeException error) {
        System.out.println(error.getMessage());
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
