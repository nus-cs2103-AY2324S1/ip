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
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Slay() {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.load();
        parser = new Parser();
    }

    public String getResponse(String userInput) {
        Command c = parser.parse(userInput);
        c.setData(this.taskList);
        CommandResult result = c.execute();
        storage.save(this.taskList);
        return ui.showResultToUser(result);
    }
}
