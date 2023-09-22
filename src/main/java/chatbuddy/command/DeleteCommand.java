package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.Task;
import chatbuddy.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.deleteTask(taskNum);
        storage.save(tasks);
        return ui.showTaskDeletion(task, tasks.getSize());
    }
}
