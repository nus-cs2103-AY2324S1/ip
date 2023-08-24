import java.util.Scanner;
public class Duke {

    private static final String INDENT = "    ";
    public static void printGreeting() {
        printHorizontalLine();
        printIndented("Hello! I'm Davidson");
        printIndented("What can I do for you?");
        printHorizontalLine();
    }
    public static void printExit() {
        printHorizontalLine();
        printIndented("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printIndented(String message) {
        System.out.println(INDENT + message);
    }

    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        printGreeting();

        while (true) {
            input = scanner.nextLine();

            if ("bye".equalsIgnoreCase(input)) {
                printExit();
                break;
            }

            printHorizontalLine();
            printIndented(input);
            printHorizontalLine();
        }

        scanner.close();
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        echoMessages();
    }
}
