package duke;

import java.util.Scanner;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the main class for the Duke chatbot application.
 */
public class Duke {

    private final String name = "Paimon";
    private TaskList taskList = new TaskList();

    /**
     * Greets the user, handles user interactions, and displays farewell message upon exit.
     */
    public void greetAndFarewell() {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Parser.readTask(command, taskList);
            command = scanner.nextLine();
        }

        scanner.close();
        Ui.exit();
    }

    public String reply(String command) {
        return Parser.readTask(command, taskList);
    }

    /**
     * Entry point of the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greetAndFarewell();
    }
}
