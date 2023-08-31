package cloud.main;

import java.util.Scanner;



/**
 * The chatbot's main class.
 */
public class Cloud {
    /** The scanner used to read commands */
    private static Scanner SCANNER = new Scanner(System.in);

    /**
     * Handles all String commands.
     *
     * @param command The command to handle.
     */
    private static void handle(String command) {
        switch (command) {
        case "bye":
            Cloud.say("\\o");
            System.exit(0);
            break;
        default:
            Cloud.say(
                String.format(
                    "Unknown command: \"%s\"",
                    command
                )
            );
            break;
        }
    }

    /**
     * Prints the specified text as output.
     *
     * @param text The text to print.
     */
    private static void say(String text) {
        System.out.println(
            String.format(
                ">>> %s",
                text
            )
        );
    }

    /**
     * The chatbot's main method.
     */
    public static void main(String[] args) {
        Cloud.say("Cloud online. Now listening for commands.");

        while (true) {
            System.out.println("");

            String command = Cloud.SCANNER.nextLine();
            Cloud.handle(command);
        }
    }
}
