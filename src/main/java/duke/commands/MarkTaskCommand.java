package duke.commands;

import duke.exceptions.DukeInvalidArgumentException;
import duke.tasks.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTaskCommand extends Command {

    /**
     * Constructs a new MarkTaskCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public MarkTaskCommand(TaskList taskList, String args) {
        super(CommandType.MARK_TASK, taskList, args);
    }

    /**
     * Marks a task as done in the list of tasks.
     */
    @Override
    public String execute() {
        try {
            return this.taskList.markTaskDone(Integer.parseInt(args));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
        }
    }
}
