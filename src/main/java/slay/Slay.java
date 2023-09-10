package slay;

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

//    /**
//     * Runs the program until termination.
//     */
//    public void run() {
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            String fullCommand = ui.readCommand();
//            Command c = parser.parse(fullCommand);
//            c.setData(this.tasks);
//            CommandResult result = c.execute();
//            ui.showResultToUser(result);
//            isExit = ExitCommand.isExit(c);
//        }
//        storage.save(this.tasks);
//    }

    public String getResponse(String userInput) {
        Command c = parser.parse(userInput);
        c.setData(this.tasks);
        CommandResult result = c.execute();
        return ui.showResultToUser(result);
    }
}
