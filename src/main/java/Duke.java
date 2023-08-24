import java.util.Scanner;

/**
 * Main class of Duke bot.
 * This class performs simple input and output handling and calls
 * appropriate functions from other classes.
 */
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

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        System.out.println();
        String userInput = scanner.nextLine();
        Instruction instruction = Parser.parse(userInput, taskList);
        drawLine();

        while (true) {
            if (instruction != null) {
                if (instruction instanceof Instruction.Exit) {
                    break;
                } else {
                    instruction.execute();
                }
            } else {
                System.out.println("\tInvalid input. Try again.");
            }

            drawLine();
            System.out.println();
            userInput = scanner.nextLine();
            instruction = Parser.parse(userInput, taskList);
            drawLine();
        }
    }

    /**
     * Greets and calls handleUserInput and finally says goodbye.
     *
     * @param args Not used currently.
     */
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
