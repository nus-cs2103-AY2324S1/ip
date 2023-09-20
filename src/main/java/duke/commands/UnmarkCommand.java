package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {

    private int taskIndex;

    /**
     * Creates UnmarkCommand object with specified index of task to be deleted.
     *
     * @param taskIndex Index of task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes unmark command. Unmarks task in TaskList, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.unmark(this.taskIndex);
        store.saveTasks(tasks);
        return ui.showUnmark(tasks.getTask(this.taskIndex));
    }

}
