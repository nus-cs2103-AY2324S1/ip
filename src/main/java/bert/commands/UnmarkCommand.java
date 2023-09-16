package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;
import bert.ui.Ui;

/**
 * Represents a command that marks a task on a task list as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE = "OK, I've marked this task as not done yet:\n  %1$s";
    private final int index;

    /**
     * Constructs a UnmarkCommand instance containing the index of a task to be marked as not done.
     *
     * @param index the index of a specific task in a task list
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.unmark(this.index);
        ui.showResult(String.format(MESSAGE, t));
    }
}
