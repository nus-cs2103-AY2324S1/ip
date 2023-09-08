package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.ui.Ui;

/** MarkCommand represents a command to mark a task as done. */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int taskNum;

    /**
     * Creates an instance of a MarkCommand with the given task number.
     *
     * @param taskNum The task number of the task to mark as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsDone(taskNum);
        return ui.showMarkTask(task);
    }
}
