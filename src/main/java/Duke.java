import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    static final String name = "Atlas";
    static int taskCount = 0;
    static final int maxTaskCount = 100;
    static String[] taskList = new String[maxTaskCount];


    public static void main(String[] args) {
        greet();
        listen();
    }

    /**
     * Prints greeting to console.
     */
    public static void greet() {
        String logo =
                "        _______ _                _____ \n" +
                        "     /\\|__   __| |        /\\    / ____|\n" +
                        "    /  \\  | |  | |       /  \\  | (___  \n" +
                        "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n" +
                        "  / ____ \\| |  | |____ / ____ \\ ____) |\n" +
                        " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.printf("Hello, I'm %s!%n", Duke.name);
        System.out.println("What would you like me to do today?");
        printHorizontalLine();
    }

    /**
     * Prints goodbye to console.
     */
    public static void exit() {
        System.out.println("Goodbye!");
    }

    /**
     * Listens and executes commands
     */
    public static void listen() {
        String command;
        final String listCommand = "list";
        final String exitCommand = "bye";
        boolean exitChatbot = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!exitChatbot) {
            try {
                command = reader.readLine();
                printHorizontalLine();
                switch (command) {
                    case listCommand:
                        printList();
                        break;
                    case exitCommand:
                        exitChatbot = true;
                        break;
                    default:
                        addItem(command);
                }
            } catch (IOException e) {
                System.out.println("Unable to read command, exiting");
                printHorizontalLine();
                exitChatbot = true;
            } finally {
                if (!exitChatbot) {
                    printHorizontalLine();
                }
            }
        }
        exit();
        printHorizontalLine();
    }

    /**
     * Adds task to list. If the list is already full, an error message is
     * instead printed and returned. If not, the item is added to the list.
     * @param item Item to add to list
     */
    public static void addItem(String item) {
        if (taskCount == maxTaskCount) {
            System.out.println("Unable to add task due to exceeding " +
                    "max task count");
            return;
        }
        taskList[taskCount++] = item;
        System.out.printf("added: %s%n", item);
    }

    /**
     * Prints list in the order in which items are added.
     */
    public static void printList() {
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf("%d. %s%n", i + 1, taskList[i]);
        }
    }

    /**
     * Prints a horizontal line containing the character '-' of width 80.
     */
    public static void printHorizontalLine() {
        final int consoleWidth = 80;
        String line = "_".repeat(consoleWidth);
        System.out.println(line);
    }
}
