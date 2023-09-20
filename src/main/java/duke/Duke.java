package duke;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    /**
     * Constructs a new Duke chatbot instance with a specified file path for data storage.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        try {
            // Initialize the tasks by loading data from storage.
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError(e);
            // If there's an error loading tasks, create a new empty task list.
            List<Task> newTaskList = new ArrayList<>();
            tasks = new TaskList(newTaskList);
        }
    }

    /**
     * Gets the storage instance.
     *
     * @return The storage instance.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Gets the task list.
     *
     * @return The task list.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user's input.
     * @return A response generated based on the user's input.
     */
    public String getResponse(String input) {
        String result = "";
        try {
            // Parse the user's input and update the result and storage accordingly.
            result += Parser.parseCommand(input, tasks);
            storage.writeLine(tasks);
        } catch (DukeException e) {
            Ui.showLoadingError(e);
        }
        return result;
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        Ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Ui.showLine();
            String result = Parser.parseCommand(command, tasks);
            Ui.showMessage(result);
            try {
                storage.writeLine(tasks);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            }
            Ui.showLine();
            command = scanner.nextLine();
        }
        Ui.showByeMessage();
        scanner.close();
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
