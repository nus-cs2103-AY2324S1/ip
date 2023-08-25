package duke.ui;

import java.util.Scanner;

public class UI {

    private final String name;
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean active = false;

    private enum Colors {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        ;

        private final String code;

        Colors(String c) {
            code = c;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public UI(String name) {
        this.name = name;
        UI.active = true;
        // Send Greeting Message
        sendMessage(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));
    }

    public static void exit() {
        sendMessage(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String msgs) {
        if (active) {
            printLine();
            for (String msg : msgs.split("\n")) {
                System.out.println("\t" + Colors.CYAN + msg + Colors.RESET);
            }
            printLine();
        }
    }

    public static void sendError(String msgs) {
        printLine();
        for (String msg : msgs.split("\n")) {
            System.out.println("\t" + Colors.RED + msg + Colors.RESET);
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static String readMessage() {
        return scanner.nextLine();
    }

}
