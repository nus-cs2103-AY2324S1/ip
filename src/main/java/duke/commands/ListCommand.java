package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to display the current tasks in the task list to the user.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                output += (i + 1) + "." + taskList.getTask(i) + "\n";
            }
            return output;
        } catch (InvalidTaskIndexException ex) {
            return "Something went wrong.";
        }
    }
}
