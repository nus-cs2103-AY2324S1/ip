import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke.botPrintBr();
        Duke.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Duke.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            // Echo user input
            Duke.botPrintMessage(userInput);
            Duke.botPrintBr();
            userInput = scanner.nextLine();
        }

        Duke.botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        Duke.botPrintBr();
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    private static void botPrintMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line break to the standard output.
     */
    private static void botPrintBr() {
        String lineBreak = "---------------------------------------------------------------------";
        Duke.botPrintMessage(lineBreak);
    }
}
