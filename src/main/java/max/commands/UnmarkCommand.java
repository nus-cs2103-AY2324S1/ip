package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.Task;
import max.ui.Ui;

/**
 * Represents list command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskNumber;

    /**
     * Specifies index of task number to be unmarked.
     *
     * @param taskNumber Index of task to be unmarked
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    /**
     * Executes unmark command. Updates done status of task in task list, updates storage, prints success message.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task unmark = tasks.getList().get(taskNumber - 1);
        tasks.unmark(taskNumber);
        storage.writeToFile(tasks);
        ui.showUnmark(unmark);
    }
    /**
     * Checks if command is an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
