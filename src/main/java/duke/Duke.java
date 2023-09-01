/**
 * ip Project duke.Duke Chat bot
 *
 * @author Aaron Tay
 * @since 2023-08-24
 */
package duke;

import java.io.IOException;

import command.Command;
import exception.DukeException;

/**
 * Duke class instantiates a new Chat Bot.
 */
public class Duke {

    /** Reads and writes task data into file */
    private Storage storage;
    /** List of tasks input by user or loaded from file */
    private TaskList tasks;
    /** Handles user interactions */
    private Ui ui;

    /**
     * Constructs a duke.Duke object with the specified file path.
     *
     * @param filePath The path where the ChatBot loads data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Controls the flow of events of the ChatBot. When active, ChatBot will get user input
     * and execute the given commands.
     */
    public void run() {
        ui.startBot();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }

        ui.endBot();
    }

    /**
     * The main entry point for the duke.Duke ChatBot application.
     * Initialise an instance of duke.Duke with the specified file path
     * and starts the duke.Duke application.
     *
     * @param args command.Command-line arguments provided when running the application (not used).
     */
    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();
    }

}
