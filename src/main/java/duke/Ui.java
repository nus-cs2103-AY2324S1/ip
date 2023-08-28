package duke;

import java.util.Scanner;

/**
 * Represents the user interface of duke that handles reading input and
 * displaying output.
 */
public class Ui {
    private Scanner sc;

    /** Constructs a Ui. */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private void drawLine() {
        int lineLength = 60; // Adjust the length of the line as needed
        char horizontalLineChar = '_'; // Unicode character for a horizontal line

        for (int i = 0; i < lineLength; i++) {
            System.out.print(horizontalLineChar);
        }
        System.out.println();
    }

    /**
     * Displays the output to the ui.
     *
     * @param output Output to be displayed.
     */
    public void printOutput(String output) {
        System.out.println(output);
        drawLine();
    }

    /**
     * Shows the welcome message when Ui is created.
     */
    public void showWlcmMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello from\n" + logo;
        printOutput(welcomeMsg);

        // greet the users
        String greetings = "Hello! I'm Orion\n"
                + "What can I do for you?\n";
        printOutput(greetings);

    }

    /**
     * Shows the error message if there is an error loading previous data from local disk.
     */
    public void showLoadingError() {
        printOutput("Oops!!! There was an error when loading file from local disk.");
    }

    /**
     * Reads the input entered by user.
     * @return The input entered by user.
     */
    public String readCommand() {
        if (!this.sc.hasNextLine()) {
            return "";
        }

        return sc.nextLine();
    }

    /**
     * Closes the Ui.
     */
    public void closeUi() {
        this.sc.close();
    }
}
