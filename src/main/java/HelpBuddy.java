/**
 * A HelpBuddy class that produces messages to user.
 */

public class HelpBuddy {
    /** A string that produces a line to segment messages produced. */
    private final static String horizontal = "____________________________________________________________";

    /**
     * Returns hello message.
     */
    public static String printHelloMessage() {
        return "Hello! I'm HelpBuddy.\n" + "What can I do for you?";
    }

    /**
     * Returns bye message.
     */
    public static String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints both hello and bye message
     */
    public static void printStandardMessage() {
        System.out.println(horizontal);
        System.out.println(HelpBuddy.printHelloMessage());
        System.out.println(horizontal);
        System.out.println(HelpBuddy.printByeMessage());
        System.out.println(horizontal);
    }
}
