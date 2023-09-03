package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * A command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Command to exit the application.
     */
    public static final String COMMAND_EXIT = "bye";

    /**
     * Constructor for the ExitCommand class.
     *
     * @param params Parsed user input.
     */
    public ExitCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 1) {
            throw new InvalidCommandException("Add todo command format is wrong");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printGoodbyeMessage();
        ui.closeUi();
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
