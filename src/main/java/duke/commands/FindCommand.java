package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a command to find all tasks with a given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates FindCommand object.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    /**
     * Executes find command. Prints all tasks in TaskList with keyword.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.find(this.keyword);
        ui.showFindCommandList(filteredTasks, this.keyword);
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
