package slay;

import javafx.application.Platform;
import slay.command.Command;
import slay.command.CommandResult;
import slay.command.ExitCommand;

/**
 * Entry point of the TaskList application.
 * Initializes the application and starts the interaction with the user.
 */
public class Slay {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Slay() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
        parser = new Parser();
    }

    /**
     * Gets response to a user input.
     *
     * @param userInput Input of user waiting for response from the slay.
     * @return String of response to the user.
     */
    public String getResponse(String userInput) {
        Command c = parser.parse(userInput);
        c.setData(this.tasks);
        assert this.tasks != null;
        CommandResult result = c.execute();
        storage.save(this.tasks);
        return ui.showResultToUser(result);
    }
}
