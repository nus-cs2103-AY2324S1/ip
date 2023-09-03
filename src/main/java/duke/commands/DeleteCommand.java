package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidTaskIndexException;
import duke.tasks.Task;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int deleteIdx;

    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Task temp = taskList.getTask(this.deleteIdx - 1);
            taskList.removeTask(this.deleteIdx - 1);
            String output1 = Ui.divider + "\nNoted. I've removed this task:\n" + "    " + temp
                    + "\nNow you have " + Integer.toString(taskList.size())
                    + " tasks in the list.\n" + Ui.divider + "\n";
            System.out.println(output1);
        } catch (InvalidTaskIndexException ex) {
            System.out.println(ex);
        }
    }
}
