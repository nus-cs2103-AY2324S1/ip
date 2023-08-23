import java.util.Scanner;

public class Duke {

    /**
     * Draws a line separating each conversation.
     *
     */
    public static void drawLine() {
        System.out.println("\t____________________________________________________________");
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public static void handleUserInput() {
        String userInput;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        userInput = scanner.nextLine();
        drawLine();
        while (!userInput.equals("bye")) {
            System.out.println("\t" + userInput);
            drawLine();
            System.out.println();
            userInput = scanner.nextLine();
            drawLine();
        }
    }

    public static void main(String[] args) {
        drawLine();
        System.out.println("\tHello I am Vishnu.");
        System.out.println("\tWhat can I do for you?");
        drawLine();
        handleUserInput();
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine();
    }
}
