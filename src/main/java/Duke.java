public class Duke {
    private static final String HORZ_LINE = "____________________________________________________________";
    private static final String BOT_NAME = "SHIBA-BOT";

    public static void main(String[] args) {
        printGreeting();
        printBye();
    }

    /** Prints the greeting message. */
    private static void printGreeting() {
        System.out.println(HORZ_LINE);
        System.out.println("Woof! I'm " + BOT_NAME);
        System.out.println("What can I bark at you?");
        System.out.println(HORZ_LINE);
    }

    /** Prints the bye message */
    private static void printBye() {
        System.out.println("Woof! Hope to bark at you again soon!");
        System.out.println(HORZ_LINE);
    }
}
