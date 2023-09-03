package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int markIdx;

    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.getTask(this.markIdx - 1).markDone();
            System.out.println("Nice! I've marked this task as done");
            System.out.println("\t" + taskList.getTask(this.markIdx - 1));
        } catch (InvalidTaskIndexException ex) {
            System.out.println(ex);
        }

    }
}
