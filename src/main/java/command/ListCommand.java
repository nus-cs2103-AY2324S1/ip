package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to list tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command, displaying the list of tasks.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        if (taskList.getListInString().equals("")) {
            return taskList.NumberOfTaskListInString();
        }
        return taskList.getListInString();
    }

}
