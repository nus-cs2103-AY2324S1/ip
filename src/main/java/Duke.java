import java.util.Scanner;

public class Duke {
    private static final String HORZ_LINE = "____________________________________________________________";
    private static final String BOT_NAME = "SHIBA-BOT";
    private static final String BYE_COMMAND = "bye";

    public static void main(String[] args) {
        printGreeting();
        processUserInputs();
        printBye();
    }

    /** Prints the greeting message. */
    private static void printGreeting() {
        System.out.println(HORZ_LINE);
        printWithIndent("Woof! I'm " + BOT_NAME);
        printWithIndent("What can I bark at you?");
        System.out.println(HORZ_LINE);
    }

    /** Prints the bye message */
    private static void printBye() {
        System.out.println(HORZ_LINE);
        printWithIndent("Woof! Hope to bark at you again soon!");
        System.out.println(HORZ_LINE);
    }

    private static void printWithIndent(String message) {
        System.out.println(" " + message);
    }

    /** Continually processes user input until bye command is issued */
    private static void processUserInputs() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (BYE_COMMAND.equals(input)) {
                return;
            } else {
                System.out.println(HORZ_LINE);
                printWithIndent(input);
                System.out.println(HORZ_LINE);
            }
        }
    }
}
