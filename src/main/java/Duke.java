import java.util.Scanner;

public class Duke {
    public static final String NAME = "Buzu";
    private static final String DIVIDER = "__________________________________________________";

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
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            say(command);
        }
        say("Bye! Hope to see you again soon!");
    }
}
