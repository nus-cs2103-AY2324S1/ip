package duke;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Initialises Duke and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;

    private TaskList list;

    private Ui ui;

    private Parser parser;

    /**
     * Initialises the application with the necessary components.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        parser = new Parser(ui);
        try {
            list = new TaskList(storage.readFile());
        } catch (Exception e) {
            ui.showError("Error reading from file.");
            list = new TaskList();
        }
    }

    /**
     * Runs the application.
     * Starts the interaction with the user.
     */
    public void run() {
        ui.showStartMessage();

        Command command = new InvalidCommand();
        while (command.type != Command.CommandType.BYE) {
            command = parser.parseCommand(ui.readCommand());
            command.execute(list, ui);
        }

        // Write to file
        try {
            storage.writeFile(list);
        } catch (Exception e) {
            ui.showError("Error writing to file.");
        }
    }

    /**
     * Starts the application by calling the run method.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
