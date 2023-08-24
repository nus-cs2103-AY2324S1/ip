import java.util.Scanner;

/**
 * Entry point to run the application.
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
            app.executeCommand(input);
        }
    }
}
