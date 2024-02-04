package duke;

import duke.command.ByeCommand;
import duke.command.Command;
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
     * Parses the user input and executes the command.
     * 
     * @param input The user input.
     * @return The response to the user input.
    */
    public String getResponse(String input) {
        Command command = parser.parseCommand(input.trim().split(" "));
        assert command != null : "Command should not be null";
        if (command instanceof ByeCommand){
            try {
                command.execute(list, ui);
                storage.writeFile(list);
            } catch (Exception e) {
                return ui.showError("Error writing to file.");
            }
        }
        return command.execute(list, ui);
    }
}
