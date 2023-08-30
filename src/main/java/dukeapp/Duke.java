package dukeapp;

import dukeapp.exceptions.UnknownCommandException;

import java.util.Scanner;

/**
 * Serves as the entry point to run the application.
 */
public class Duke {
    public static void main(String[] args) {
        DukeApp app = new DukeApp();

        // Greet the user
        System.out.println(DukeConstants.GREETING_MESSAGE);

        Scanner scanner = new Scanner(System.in);

        // Read user input until program exits
        while (true) {
            String input = scanner.nextLine();
            try {
                app.executeCommand(input);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
