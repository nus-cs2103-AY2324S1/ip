import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm Skye, your personal task assistant. \nWhat can I do for you?");

        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                scanner.close();
                System.exit(0);
            }

            printMessage(userInput);
        }
    }

    private static void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    private static void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }
}