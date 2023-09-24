package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;

/**
 * Marks a task in the list as incomplete.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Creates an UnmarkCommand that marks a task in the list as incomplete.
     * @param input The user input indicating which task to mark as incomplete.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList list) throws DukeException {
        return list.setTaskIncomplete(this.input);
    }
}
