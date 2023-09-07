package duke.commands;

import java.io.IOException;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The DeleteTaskCommand deletes a given task, updates the .txt file
 * and display task is deleted message when executed.
 */
public class DeleteTaskCommand extends Command {

    /** ID of task to be deleted. */
    private final int taskID;
    /**
     * Constructor to initialize DeleteTaskCommand.
     *
     * @param taskID ID of task to be deleted.
     */
    public DeleteTaskCommand(int taskID) {
        this.taskID = taskID;
    }
    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException, IOException {
        Task deletedTask = taskList.deleteTask(taskID);
        Storage.save(taskList);
        return message.showTaskDeleted(deletedTask, taskList.countTasks());
    }
}
