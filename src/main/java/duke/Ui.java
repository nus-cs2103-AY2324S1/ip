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
        if (message.equals("undefined")) {
            System.out.println("⚠ Sorry! I am not able to understand you. Try another language:D");
        } else if (message.equals("task not found")) {
            System.out.println("⚠ Oops! Cannot find task:(");
        } else if (message.equals("todo error")) {
            System.out.println("⚠ Oops! Need description for the todo:(");
        } else if (message.equals("deadline error")) {
            System.out.println("⚠ Oops! Need description and formatted by date for the deadline:(");
        } else if (message.equals("event error")) {
            System.out.println("⚠ Oops! Need description, from and to date for the event:(");
        } else {
            System.out.println("⚠ Oops! Something went wrong:(");
        }
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
