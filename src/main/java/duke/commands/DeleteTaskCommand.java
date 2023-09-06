package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Constructs a new DeleteTaskCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public DeleteTaskCommand(TaskList taskList, String args) {
        super(CommandType.DELETE_TASK, taskList, args);
    }

    /**
     * Deletes a task from the list of tasks.
     */
    @Override
    public String execute() {
        try {
            return this.taskList.deleteTask(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
