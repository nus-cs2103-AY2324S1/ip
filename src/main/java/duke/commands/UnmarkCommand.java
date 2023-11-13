package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;

/**
 * Command to unmark a task in the task list.
 */
public class UnmarkCommand extends Command {
    private final int unmarkIdx;

    public UnmarkCommand(int unmarkIdx) {
        this.unmarkIdx = unmarkIdx;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.getTask(this.unmarkIdx - 1).unMarkDone();
            String output = "OK, I've marked this task as not done yet:\n";
            output += "\t" + taskList.getTask(this.unmarkIdx - 1);
            return output;
        } catch (InvalidTaskIndexException ex) {
            return ex.toString();
        }
    }
}
