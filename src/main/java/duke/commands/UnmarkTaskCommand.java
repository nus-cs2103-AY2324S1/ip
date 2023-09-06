package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkTaskCommand extends Command {

    /**
     * Constructs a new UnmarkTaskCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public UnmarkTaskCommand(TaskList taskList, String args) {
        super(CommandType.UNMARK_TASK, taskList, args);
    }

    /**
     * Unmarks a task as done in the list of tasks.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        try {
            return this.taskList.unmarkTaskDone(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
