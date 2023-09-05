package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find a task from a given string prefix.
 */
public class FindCommand extends Command {
    private String prefix;

    public FindCommand(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        int count = 0;
        try {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.getTask(i).getTaskName().matches("(.*)" + this.prefix + "(.*)")) {
                    output += (count + 1) + "." + taskList.getTask(i) + "\n";
                    count++;
                }
            }
            return output;
        } catch (InvalidTaskIndexException ex) {
            return "Something went wrong.";
        }
    }
}
