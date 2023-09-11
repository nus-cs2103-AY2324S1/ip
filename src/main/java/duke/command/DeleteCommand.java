package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

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

    /**
     * Deletes a task and displays the task deleted.
     *
     * @param taskList list of tasks
     * @param storage  storage
     * @return GobbleMessage object containing the message to be displayed.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveListToDisk(taskList.getTasks());
        return GobbleMessage.getGobbleDialog("Noted. I've removed this task:\n" + task + "\nNow you have "
                + taskList.getSize() + " tasks in the list.", "Delete");
    }

}
