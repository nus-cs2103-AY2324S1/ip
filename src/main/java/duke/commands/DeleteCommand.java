package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

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
    public String execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        Task displayTask = tasks.getTask(this.taskIndex);
        tasks.delete(this.taskIndex);
        return ui.showDelete(displayTask, (tasks.getLength() - 1));
    }

}
