package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;
import bert.ui.Ui;

/**
 * Represents a command that marks a task on a task list as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String MESSAGE = "Nice! I've marked this task as done:\n  %1$s";
    private final int index;

    /**
     * Constructs a MarkCommand instance containing the index of a task to be marked as done.
     *
     * @param index The index of a specific task in a task list.
     */
    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.mark(this.index);
        ui.showResult(String.format(MESSAGE, t));
    }
}
