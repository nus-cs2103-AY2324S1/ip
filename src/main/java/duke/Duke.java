package duke;

import commands.Command;
import exceptions.JamesBondException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


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
     * * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command c = Parser.parseCommand(input);
        return c.runCommand(tasks, ui, storage);
    }


}
