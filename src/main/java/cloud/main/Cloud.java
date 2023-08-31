package cloud.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * The chatbot's main class.
 */
public class Cloud {
    /** The scanner used to read inputs */
    private static Scanner SCANNER = new Scanner(System.in);

    /** The list of stored user TODOs */
    private static List<String> LIST = new ArrayList<>();

    /**
     * Handles all String inputs.
     *
     * @param input The String to handle.
     */
    private static void handle(String input) {
        switch (input) {
        case "list":
            // Lists TODOs
            Cloud.say("Your TODO list:");
            for (int i = 0; i < Cloud.LIST.size(); i++) {
                Cloud.say(
                    String.format(
                        "%d) %s",
                        i + 1,
                        Cloud.LIST.get(i)
                    )
                );
            }
            break;
        case "bye":
            // Ends chat session
            Cloud.say("\\o");
            System.exit(0);
            break;
        default:
            // Stores new TODO
            Cloud.LIST.add(input);
            Cloud.say(
                String.format(
                    "Added TODO: \"%s\"",
                    input
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
        System.out.println(text);
    }

    /**
     * The chatbot's main method.
     */
    public static void main(String[] args) {
        Cloud.say("Cloud online.");

        while (true) {
            System.out.println("");
            System.out.print(">>> ");

            String input = Cloud.SCANNER.nextLine();
            Cloud.handle(input);
        }
    }
}
