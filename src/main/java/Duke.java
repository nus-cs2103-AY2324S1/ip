public class Duke {
    private final static String CHATBOT_NAME = "Fluke";
    private final static String LOGO =
            "    ________      __      \n" +
            "   / ____/ /_  __/ /_____ \n" +
            "  / /_  / / / / / //_/ _ \\\n" +
            " / __/ / / /_/ / ,< /  __/\n" +
            "/_/   /_/\\__,_/_/|_|\\___/ \n" +
            "                          ";

    public static void main(String[] args) {
        System.out.println(LOGO);
        addHorizontalLine();
        greet();
        sayBye();
    }

    private static void greet() {
        System.out.println(
                "Hello! I'm " + CHATBOT_NAME + "\n" +
                "What can I do for you?"
        );
        addHorizontalLine();
    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        addHorizontalLine();
    }

    private static void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
