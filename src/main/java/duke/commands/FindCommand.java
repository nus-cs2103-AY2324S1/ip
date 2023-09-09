package duke.commands;

import java.util.ArrayList;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

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
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.find(this.keyword);
        return ui.showFindCommandList(filteredTasks, this.keyword);
    }

}
