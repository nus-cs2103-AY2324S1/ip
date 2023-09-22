package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

/**
 * Represents a command to delete all marked tasks.
 * Inherits from the Command class.
 */
public class DeleteMarkedCommand extends Command {

    /**
     * Delete all marked tasks, inform user.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @return Response as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        String response = "I have deleted the following tasks you done:\n";
        response = response + tasks.deleteMarkedTasks();
        storage.saveTasks(tasks);
        return response;
    }
}
