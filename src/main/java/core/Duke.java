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
     */
    public Duke() {
        Duke.filepath = "src/data/tasks.txt";
        ui = new Ui();
        storage = new Storage(Duke.filepath);
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
     * Runs the load function in Storage, called in MainWindow.
     */
    public void loadByDuke() {
        Duke.refresh();
    }

    public static void refresh() {
        Storage.refresh(taskList);
    }

    /**
     * Bridges GUI and Program. Takes in user command scanned by MainWindow and parses it.
     * @param reply
     * @return chatbot's reply
     */
    public String getResponse(String reply) {
        return CommandParser.parse(reply).execute(taskList, ui, storage);
    }
}
