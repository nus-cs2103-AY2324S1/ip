package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;
import chatbuddy.task.Task;

/** UnmarkCommand represents a command to mark a task as not done. */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private int taskNum;

    /**
     * Creates an instance of an UnmarkCommand with the given task number.
     *
     * @param taskNum The task number of the task to mark as not done.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsNotDone(taskNum);
        ui.showUnmarkTask(task);
    }
}
