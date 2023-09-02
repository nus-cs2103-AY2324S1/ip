package duke;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for Duke.
     * Creates a new Duke object with the given file path.
     * Loads the tasks from the file path.
     * If the file path does not exist, creates a new file.
     *
     * @param filePath The path to the file where the tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method for Duke.
     * Runs the duke program with the file path.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/stored_tasks").run();
    }

    /**
     * Runs the duke program.
     * Shows the welcome, exit message.
     * Deals with User input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (! isExit) {
            try {
                String fullCommand = ui.getUserInput();
                isExit = Parser.checkCommandType(fullCommand, tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showExit();
    }
}