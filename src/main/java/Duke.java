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
    private static String[] listOfCommands = new String[100];
    private static int nextCommandIndex = 0;

    public static void main(String[] args) {
        // introduce Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        greet();
        addHorizontalLine();
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
                case "list":
                    list();
                    break;
                default:
                    addCommand(nextCommand);
            }
            addHorizontalLine();
        }
    }

    private static void greet() {
        System.out.println(
                "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                "Feeling lucky today?"
        );
    }

    private static void sayBye() {
        System.out.println("Bye. Good luck!");
    }

    private static void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void addCommand(String str) {
        listOfCommands[nextCommandIndex] = str;
        nextCommandIndex += 1;
        System.out.println("added: " + str);
    }

    private static void list() {
        for (int i = 0; i < 100; i++) {
            if (listOfCommands[i] == null) {
                break;
            } else {
                String command = listOfCommands[i];
                int number = i + 1;
                System.out.println(number + ". " + command);
            }
        }
    }
}
