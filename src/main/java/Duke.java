import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Duke {
    static String name = "Atlas";
    public static void main(String[] args) {
        greet();
        listen();
        exit();
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
        printHorizontalLine();
    }

    /**
     * Listens and executes commands
     */
    public static void listen() {
        String command;
        String exitCommand = "bye";
        boolean exitChatbot = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!exitChatbot) {
            try {
                command = reader.readLine();
                printHorizontalLine();
                if (command.equals(exitCommand)) {
                    exitChatbot = true;
                } else {
                    System.out.println(command);
                    printHorizontalLine();
                }
            } catch (IOException e) {
                printHorizontalLine();
                System.out.println("Unable to read command, exiting");
                exitChatbot = true;
            }
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
