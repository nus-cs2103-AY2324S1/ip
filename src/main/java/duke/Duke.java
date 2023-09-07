package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents a Duke object that runs the duke program.
 */
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
        new Duke("./data/stored_tasks.txt").run();
    }

    /**
     * Runs the duke program.
     * Shows the welcome, exit message.
     * Deals with User input.
     */
    public void run() {
        ui.showWelcome();
        String commandGiven = "";
        while (!commandGiven.equals("bye")) {
            try {
                String fullCommand = ui.getUserInput();
                commandGiven = Parser.checkCommandType(fullCommand, tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showExit();
    }

    public String getResponse(String input) {
        String commandGiven = "";
        if (tasks == null) {
            tasks = new TaskList();
        } else {
            try {
                commandGiven = Parser.checkCommandType(input, tasks, ui, storage);
                if (commandGiven.equals("bye")) {
                    commandGiven = ui.showExit();
                    System.exit(0);
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return commandGiven;
    }
}
