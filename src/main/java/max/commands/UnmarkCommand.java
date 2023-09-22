package max.commands;

import max.exception.InvalidArgumentException;
import max.exception.MaxException;
import max.storage.Storage;
import max.tasks.Task;
import max.tasks.TaskList;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        try {
            Task unmark = tasks.getList().get(taskNumber - 1);
            tasks.unmark(taskNumber);
            storage.writeToFile(tasks);
            return ui.showUnmark(unmark);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Please ensure the number is within the index range of your list!");
        }
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
