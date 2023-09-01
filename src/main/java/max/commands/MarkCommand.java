package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

/**
 * Represents mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskNumber;
    /**
     * Specifies index of task number to be unmarked.
     *
     * @param taskNumber Index of task to be unmarked
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    /**
     * Executes mark command. Updates done status of task in task list, updates storage, prints success message.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task mark = tasks.getList().get(taskNumber - 1);
        tasks.mark(taskNumber);
        storage.writeToFile(tasks);
        ui.showMark(mark);
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
