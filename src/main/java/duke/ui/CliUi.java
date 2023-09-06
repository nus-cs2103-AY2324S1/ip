package duke.ui;

/**
 * Handles user interface outputs
 */
public class CliUi {

    /** The divider to be used to separate outputs */
    public static final String DIVIDER = "____________________________________________________________";

    /**
     * Prints single-line input, with divider above and below.
     *
     * @param input The input to be printed.
     */
    public static void println(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    /**
     * Prints multiline inputs, with divider above and below.
     *
     * @param input The inputs to be printed.
     */
    public static void printlns(String[] input) {
        System.out.println(DIVIDER);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user.
     */
    public static void greet() {
        CliUi.printlns(new String[] { "Hello! I'm LilBro!", "What can I do for you?" });
    }

    /**
     * Bids the user goodbye.
     */
    public static void bye() {
        CliUi.println("Bye. Hope to see you again soon!");
    }
}
