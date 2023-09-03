package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates AddCommand object with specified task to be added.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes add command. Adds task to TaskList, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.add(task);
        ui.showAdd(task, tasks.getLength());
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
