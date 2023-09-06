package duke;

import java.util.Scanner;

/**
 * Represents a user interface of the chat robot.
 */
public class Ui {
    public void showLoadingError() {
        System.out.println("Generating new chat session...");
    }

    public void showSavingError() {
        System.out.println("⚠ Oops! Something wrong when closing:(");
    }

    /**
     * Prints corresponding error message according to the exception.
     * @param e the exception.
     */
    public void handleException(DukeException e) {
        String message = e.getMessage();
        String warning;
        switch (message) {
        case "todo error":
            warning = "⚠ Oops! Need description for the todo:(";
            break;
        case "deadline error":
            warning = "⚠ Oops! Need description and formatted by date for the deadline:(";
            break;
        case "event error":
            warning = "⚠ Oops! Need description, from and to date for the event:(";
            break;
        case "task not found":
            warning = "⚠ Oops! Cannot find task:(";
            break;
        case "undefined":
            warning = "⚠ Sorry! I am not able to understand you. Try another language:D";
            break;
        default:
            warning = "⚠ Oops! Something went wrong:(";
            break;
        }
        System.out.println(warning);
    }

    /**
     * Runs the UI to print start and ending messages.
     * @param p the parser.
     */
    public void run(Parser p) {
        Scanner scanner = new Scanner(System.in);
        String logo = " _ \n"
                + "| |\n"
                + "| |\n"
                + "| |___\n"
                + "|_____|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Hi! This is your intelligent friend L.\n\"Dream big.\"\n"
                + "What can I do for you today?");
        while (scanner.hasNextLine()) {
            try {
                String output = p.parse(scanner.nextLine());
                if (p.isRunning()) {
                    System.out.println(output);
                } else {
                    System.out.println("Bye!\n\"Beware the barrenness of a busy life.\"");
                    break;
                }
            } catch (DukeException e) {
                handleException(e);
            }
        }
        scanner.close();
    }
}
