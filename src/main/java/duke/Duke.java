package duke;

import java.util.Scanner;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke chatbot with the provided file path.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Gets the storage object associated with this Duke chatbot.
     *
     * @return The storage object used to load and save tasks.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Gets the task list associated with this Duke chatbot.
     *
     * @return The task list containing tasks managed by Duke.
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
            result += Parser.executeCommand(input, tasks);
            storage.save(tasks);
        } catch (DukeException e) {
            Ui.showLoadingError(e);
        }
        return result;
    }

    /**
     * Runs the Duke chatbot and handles user interactions.
     */
    public void run() {
        Ui.greet();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Ui.printLine();
            String result = Parser.executeCommand(command, tasks);
            Ui.showMessage(result);
            try {
                storage.save(tasks);
            } catch (DukeException e) {
                Ui.showLoadingError(e);
            }
            Ui.printLine();
            command = scanner.nextLine();
        }
        scanner.close();
        Ui.goodbye();
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
