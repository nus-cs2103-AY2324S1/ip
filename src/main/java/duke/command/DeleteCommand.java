package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Delete that deletes a task from the list.
 *
 * @author Joseph Oliver Lim
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDeleteTask(tasks.getTask(this.index), tasks.getCountTasks() - 1);
        tasks.removeTask(this.index);
    }
}
