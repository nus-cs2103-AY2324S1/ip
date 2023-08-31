package core;

import commands.Command;
import parser.CommandParser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Duke is a task management application that interacts with users through a command-line interface.
 * It allows users to manage tasks by adding, marking, and deleting tasks from a task list.
 */
public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static String filepath;

    /**
     * Constructs a Duke instance with the specified filepath for storage.
     *
     * @param filepath The filepath for task storage.
     */
    public Duke(String filepath) {
        Duke.filepath = filepath;
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList();
    }

    /**
     * Runs the Duke application, displaying a welcome message and handling user commands.
     * The application continues running until the user inputs an exit command.
     */
    public static void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals(null)) {
                    isExit = true;
                } else {
                    Command c = CommandParser.parse(fullCommand);
                    c.execute(taskList, ui, storage);
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
