package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;
import bert.ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE = "OK, I've marked this task as not done yet:\n  %1$s";
    private final int index;

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
