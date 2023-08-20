import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke.botPrintBr();
        Duke.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Duke.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String userInput = scanner.nextLine();
        ArrayList<String> tasks = new ArrayList<>();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Duke.botPrintMessage(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            } else {
                tasks.add(userInput);
                Duke.botPrintMessage("added: " + userInput);
            }
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
