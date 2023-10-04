package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;

/**
 * Marks a task in the list as completed.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Creates a MarkCommand that marks a specified task as completed.
     * @param input The user input indicating which task to mark as completed.
     */
    public MarkCommand(String input) {
        super();
        this.input = input;
    }

    @Override
    public String execute(TaskList list) throws DukeException {
        return list.setTaskComplete(input);
    }
}
