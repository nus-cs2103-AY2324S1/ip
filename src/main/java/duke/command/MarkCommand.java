package duke.command;

import duke.main.*;
import duke.exception.*;
import duke.task.*;
import java.io.IOException;

/**
 * The MarkCommand represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand extends Command {
    private boolean isMark;
    private int index;

    /**
     * Constructs a MarkCommand object that marks or unmarks a task.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param isMark true to mark the task, false to unmark the task.
     */
    public MarkCommand(int index, boolean isMark) {
        super(null);
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Executes the command to mark or unmark a task in the task list.
     *
     * @param tasks The task list to interact with.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks.
     * @throws IOException If there's an error with the input/output.
     * @throws InvalidTaskNumberException If an invalid task number is encountered during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (this.index >= tasks.getSize() || this.index < 0) throw new InvalidTaskNumberException();
        Task task = tasks.getTaskAtIndex(this.index);
        if (isMark) {
            task.markTask();
            ui.showMark(task);
        } else {
            task.unmarkTask();
            ui.showUnmark(task);
        }
        storage.update(tasks.getTasks());
    }
}
