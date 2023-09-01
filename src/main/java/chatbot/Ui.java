package chatbot;

import chatbot.task.Task;

import java.util.Scanner;

/**
 * Class which deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * constructor for Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Read command.
     *
     * @return String of the command
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * print greeting message.
     */
    public void showGreeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Sara");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * print farewell message.
     */
    public void showFarewell() {
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Show error message to the client.
     *
     * @param message takes in the error message which wish to be print
     */
    public void showError(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message);
        System.out.println("    ____________________________________________________________");
    }

}
