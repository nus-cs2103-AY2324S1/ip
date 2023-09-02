package duke;

import duke.commands.Command;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.io.File;
import java.io.IOException;

/**
 * Represents the main class for the chat bot.
 * Encapsulates the logic of the chat bot's functionality.
 */
public class Duke {

    // CONSTANTS
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "duke.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path for data storage.
     */
    public Duke(String filePath) {
        TaskList tasks1;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks1 = new TaskList(storage.loadTasksFromStorage());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks1 = new TaskList();
        }
        tasks = tasks1;
    }

    public Duke() {
        TaskList tasks1;
        ui = new Ui();
        storage = new Storage(DIR_NAME + File.separator + FILE_NAME);
        try {
            tasks1 = new TaskList(storage.loadTasksFromStorage());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks1 = new TaskList();
        }
        tasks = tasks1;
    }

    /**
     * Runs the chat bot by displaying a welcome message
     * and processing user commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit && ui.checkForCommand()) { // need to check whether scanner has a next line
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);

                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
    }

    /**
     * Main method to start the chat bot.
     */
    public static void main(String[] args) {
        new Duke(DIR_NAME + File.separator + FILE_NAME).run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
