package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsNotDone(taskNum);
        storage.save(tasks);
        return ui.showUnmarkTask(task);
    }
}
