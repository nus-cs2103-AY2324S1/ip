public class Duke {
    private static final String SEPARATION_LINE  = "____________________________________________________________";
    private static final String GREETING_TEXT = "Hello, I'm Tasket\nWhat can I do for you?";
    private static final String GOODBYE_TEXT = "Bye. Hope to see you again soon!";

    public static void showGreetingText() {
        System.out.println(SEPARATION_LINE);
        System.out.println(GREETING_TEXT);
        System.out.println(SEPARATION_LINE);
    }

    public static void showGoodbyeText() {
        System.out.println(GOODBYE_TEXT);
        System.out.println(SEPARATION_LINE);
    }

    public static void main(String[] args) {
        showGreetingText();
        showGoodbyeText();
    }
}
