package duke;

import duke.command.Command;
import duke.command.DukeException;
import duke.command.Parser;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The Duke class represents a chatbot application.
 * It allows users to add, mark as done, mark as not done, delete, and list 3 different type of tasks.
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
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke/duke.txt").run();
    }

    public void run() {
        ui.showHiMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            String response = getResponse(userInput);
            System.out.println(response);
        } while (!userInput.equalsIgnoreCase("bye"));

        ui.showGoodbyeMessage();
    }
}
