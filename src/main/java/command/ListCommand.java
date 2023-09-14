package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * A command to list all tasks that are currently saved in the chatbot.
 */
public class ListCommand extends Command {
    /**
     * Command to list all tasks.
     */
    public static final String COMMAND_LIST = "list";

    /**
     * Constructor for the ListCommand class.
     *
     * @param params Parsed user input.
     */
    public ListCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 1) {
            throw new InvalidCommandException("List command format is wrong");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      UI of the application.
     * @param storage Object to handle data storage.
     * @return
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getAllTasksMessage(tasks);
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
