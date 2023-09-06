package duke;

import java.util.Scanner;

import duke.task.TaskList;

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
        Ui.greetings();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Ui.showLine();
            try {
                Parser.readTask(command, taskList);
            } catch (DukeException e) {
                Ui.showError(e);
            }
            Ui.showLine();
            command = scanner.nextLine();
        }

        scanner.close();
        Ui.exit();
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
