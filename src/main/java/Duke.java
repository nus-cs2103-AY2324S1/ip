import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String NAME = "Buzu";
    private static final String DIVIDER = "__________________________________________________";
    private static final List<String> items = new ArrayList<>();

    /**
     * Utility function to add indentation when printing line.
     *
     * @param line The line to be indented
     */
    private static void printLine(String line) {
        System.out.println("\t" + line);
    }

    /**
     * Utility function for chatbot to send a formatted response to the user.
     *
     * @param text The text to respond with
     */
    private static void say(String... text) {
        printLine(DIVIDER);
        for (String line : text) {
            printLine(line);
        }
        printLine(DIVIDER);
        System.out.println();
    }

    public static void main(String[] args) {
        say("Hello! I'm " + NAME + ".", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        boolean stopped = false;
        while (!stopped && scanner.hasNextLine()) {
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    int numItems = items.size();
                    if (numItems == 0) {
                        say("There are no items in your list.");
                    } else {
                        String[] response = new String[numItems];
                        for (int i = 0; i < numItems; i++) {
                            response[i] = (Integer.toString(i + 1) + ". " + items.get(i));
                        }
                        say(response);
                    }
                    break;
                case "bye":
                    stopped = true;
                    say("Bye! Hope to see you again soon!");
                    break;
                default:
                    items.add(command);
                    say("added: " + command);
                    break;
            }
        }
    }
}
