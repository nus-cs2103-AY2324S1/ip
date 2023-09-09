package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int markIdx;

    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            taskList.getTask(this.markIdx - 1).markDone();
            String output = "Nice! I've marked this task as done\n";
            output += "\t" + taskList.getTask(this.markIdx - 1);
            return output;
        } catch (InvalidTaskIndexException ex) {
            return ex.toString();
        }

    }
}
