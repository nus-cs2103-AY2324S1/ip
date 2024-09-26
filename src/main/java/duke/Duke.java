package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.DukeException;
import duke.task.TaskList;

/**
 * The Duke class represents a chatbot application that manages tasks.
 * Users can add, mark as done, mark as not done, delete, list, and interact with different types of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the specified file path for storage.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();

        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(this.storage.loadTasksFromFile());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input based on the provided input command.
     *
     * @param input The user input command to process.
     * @return A formatted response message based on the input command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.showError(e.getMessage());
        }
    }

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Runs the Duke application, allowing users to interact with it through the command line.
     */
    public void run() {
        this.ui.showHiMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            String response = getResponse(userInput);
            System.out.println(response);
        } while (!userInput.equalsIgnoreCase("bye"));

        this.ui.showGoodbyeMessage();
    }
}
