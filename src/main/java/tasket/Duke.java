package tasket;

import tasket.command.Command;
import tasket.command.CommandResult;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.parser.Parser;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for Tasket.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * The constructor of Duke.
     * This is used by javafx.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (TasketException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Greet the user in GUI.
     *
     * @return Greet message.
     */
    public String greet() {
        return ui.showWelcome();
    }

    /**
     * Exit the application.
     * To be used by gui.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Returns a response according to user input.
     * @param input The user input.
     * @return The response.
     */
    public CommandResult getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String response = c.execute(taskList, ui, storage);
            return new CommandResult(response, c.isExit());
        } catch (TasketException e) {
            return new CommandResult(ui.showError(e.getMessage()), false);
        }
    }

}
