package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates DeleteCommand object with specified index of task to be deleted.
     *
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes delete command. Deletes task from TaskList, and informs user of the update.
     *
     * @param tasks The TaskList containing ArrayList of tasks.
     * @param ui The UI handling user interactions.
     * @param store The DDataStorage handling data.
     * @throws DukeException If there is an error generated while command is run.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ui.showDelete(tasks.taskList.get(this.taskIndex), (tasks.taskList.size() - 1));
        tasks.delete(this.taskIndex);
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
