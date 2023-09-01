package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.util.ArrayList;

public class ListCommand extends Command{
    /**
     * Command to list all tasks.
     */
    public static final String COMMAND_LIST = "list";

    /**
     * Constructor for the ListCommand class.
     *
     * @param params Parsed user input.
     */
    public ListCommand(ArrayList<String> params) {
        super(params);
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
        tasks.printContents();
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
