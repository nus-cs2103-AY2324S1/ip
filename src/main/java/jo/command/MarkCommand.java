package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `MarkCommand` class represents a command for marking or unmarking a task in the `Jo` application.
 */
public class MarkCommand extends Command {

    private int index;
    private boolean isDone;

    /**
     * Constructs a `MarkCommand` object with the specified index of the task and its completion status.
     *
     * @param index  The index of the task to be marked or unmarked.
     * @param isDone `true` if the task should be marked as done, `false` if it should be marked as undone.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the command, marking or unmarking the specified task, updating storage, and displaying the result.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for loading and saving tasks to a file.
     * @throws JoException If an error occurs during the execution of the command, such as an invalid index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        if (this.index < 0 || this.index >= tasks.getSize()) {
            throw new JoException("This task does not exist.");
        } else {
            tasks.markTask(this.index, this.isDone);
            storage.update(tasks);
            ui.markResult(tasks.getTask(this.index), this.isDone);
        }
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since marking or unmarking a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
