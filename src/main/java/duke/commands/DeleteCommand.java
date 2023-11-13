package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int deleteIdx;

    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task temp = taskList.getTask(this.deleteIdx - 1);
            taskList.removeTask(this.deleteIdx - 1);

            String output1 = "Noted. I've removed this task:\n" + "    " + temp
                    + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            return output1;
        } catch (InvalidTaskIndexException ex) {
            return ex.toString();
        }
    }
}
