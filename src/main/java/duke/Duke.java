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
     *
     * @param filePath Path to the file where the tasks are stored.
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
     * Main method.
     * Runs duke with the given file path.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/stored_tasks").run();
    }

    /**
     * Runs duke.
     * Shows welcome message and exit message.
     * Duke will keep running until the user inputs "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
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