package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates ListCommand object.
     */
    public ListCommand() {

    }


    /**
     * Executes list command. Prints all tasks in TaskList.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ui.showList(tasks.getTaskList());
    }

    /**
     * Checks if the application should exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
