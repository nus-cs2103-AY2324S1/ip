package duke;

import java.io.File;
import java.io.IOException;

import duke.commands.Command;
import duke.tools.Parser;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

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

    /**
     * Constructs a Duke instance with a default file path.
     */
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

                assert fullCommand != null;

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
     * Starts the chat bot in the terminal.
     */
    public static void main(String[] args) {
        new Duke(DIR_NAME + File.separator + FILE_NAME).run();
    }

    /**
     * Retrieves the response to the input given via GUI
     *
     * @param input   The String of the full command.
     * @return The reply of Respironix.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            String unknownCommandReply = "I had problems with your command: " + input + "...";
            unknownCommandReply += ("\n" + e.getMessage());
            return unknownCommandReply;
        }


    }
}
