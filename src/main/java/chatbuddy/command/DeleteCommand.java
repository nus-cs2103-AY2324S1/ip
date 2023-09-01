package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;
import chatbuddy.task.Task;

/** DeleteCommand represents a command to delete a task. */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNum;

    /**
     * Creates an instance of a delete command with the given task number.
     *
     * @param taskNum The task number of the task to delete.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.deleteTask(taskNum);
        ui.showTaskDeletion(task, tasks.getSize());
    }
}
