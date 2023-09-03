package duke;

import exceptions.JamesBondException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import commands.*;

/**
 * The `Duke` class is the main class for the Duke task management application.
 * It handles the initialization of various components and the execution of the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a `Duke` object with the specified file path for data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            TaskList tasks = new TaskList();
            tasks = storage.loadTasksFromFile();
            this.tasks = tasks;
        } catch (JamesBondException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * It displays a welcome message and continuously reads user input, parsing and executing commands
     * until there is no more input.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (ui.isThereNext()) {
            String input = ui.readLine();
            Command c = Parser.parseCommand(input);
            c.runCommand(tasks, ui, storage);
        }
    }

    /**
     * The main method to start the Duke application.
     * It creates an instance of `Duke` and runs the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("/Users/jamesbond/ip/src/main/data/jamesbond.txt").run();
    }
}
