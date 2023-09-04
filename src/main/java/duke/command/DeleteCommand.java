package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.GobbleChatContainer;

/**
 * Represents a DeleteCommand class that deals with the command to delete a task.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, GobbleChatContainer chat, Storage storage) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
//        ui.showDeleteMessage(task, taskList.getSize());
        chat.addMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + taskList.getSize() + " tasks in the list.", "Delete");
        storage.saveListToDisk(taskList.getTasks());
    }

}
