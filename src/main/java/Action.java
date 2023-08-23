public class Action {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";


    /**
     * Greets the user by printing the greeting messages.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bell Curve God.");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Says goodbye to the user and exits.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
