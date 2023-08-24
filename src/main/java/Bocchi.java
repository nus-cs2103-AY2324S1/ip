public class Bocchi {
    private static final String LINE_BREAK = "___________________________________________________";

    /**
     * Outputs greeting message.
     */
    private static void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Bocchi");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs exit message.
     */
    private static void exit() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }
    /**
    /**
     * Main method.
     */
    public static void main(String[] args) {
        greet();
        exit();
    }
}
