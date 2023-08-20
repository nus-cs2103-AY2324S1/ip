import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke.botPrintBr();
        Duke.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Duke.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String userInput = scanner.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                Duke.botPrintMessage("The tasks in your list are as follows:");
                for (int i = 0; i < tasks.size(); i++) {
                    Duke.botPrintMessage(String.format("%d.%s", i + 1, tasks.get(i)));
                }
            } else if (userInput.length() >= 6 && userInput.substring(0, 4).equals("mark")) {
                try {
                    Task task = tasks.get(Integer.parseInt(userInput.substring(5)) - 1);
                    task.markAsDone();
                    Duke.botPrintMessage("Okay! I've marked the following task as done:");
                    Duke.botPrintMessage(task.toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    Duke.botPrintMessage("That is an invalid task number");
                }
            } else if (userInput.length() >= 8 && userInput.substring(0, 6).equals("unmark")) {
                try {
                    Task task = tasks.get(Integer.parseInt(userInput.substring(7)) - 1);
                    task.markAsNotDone();
                    Duke.botPrintMessage("Okay! I've marked the following task as not done:");
                    Duke.botPrintMessage(task.toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    Duke.botPrintMessage("That is an invalid task number");
                }
            } else {
                tasks.add(new Task(userInput));
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
