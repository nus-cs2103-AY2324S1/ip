package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;
import chatbuddy.task.Task;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsDone(taskNum);
        ui.showMarkTask(task);
    }
}
