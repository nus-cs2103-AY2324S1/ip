import java.util.Scanner;

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
        // introduce Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        greet();
        // initialise scanner to check for user input
        Scanner scanner = new Scanner(System.in);
        boolean waitingForInput = true;
        while (waitingForInput) {
            // check for user commands
            String nextCommand = scanner.nextLine();
            switch (nextCommand) {
                case "bye":
                    waitingForInput = false;
                    sayBye();
                    break;
                default:
                    echo(nextCommand);
            }
        }
    }

    private static void greet() {
        System.out.println(
                "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                "Feeling lucky today?"
        );
        addHorizontalLine();
    }

    private static void sayBye() {
        System.out.println("Bye. Good luck!");
        addHorizontalLine();
    }

    private static void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void echo(String str) {
        System.out.println(str);
        addHorizontalLine();
    }
}
