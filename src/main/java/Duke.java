import java.util.Scanner;

public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);


    private static void printMessage(String message) {
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println("\n" + line + "\n");
    }

    private static void printMessage(String[] message) {
        System.out.println(line);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + line + "\n");
    }

    public static void printWelcome() {
        String[] message = {
            "Hello! I'm " + name,
            "What can I do for you?"
        };

        printMessage(message);
    }

    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printWelcome();

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }

            printMessage(command);
        }

        printExit();
    }
}
