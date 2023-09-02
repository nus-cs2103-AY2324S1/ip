package duke.commands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.exceptions.InvalidIndexException;

/**
 * Represents a command that marks a task as undone.
 */
public class UnmarkCommand implements Command {

    private int index;

    /**
     * Creates a UnmarkCommand object.
     * 
     * @param matcher The matcher object that contains the user input matched to the unmark regex.
     */
    public UnmarkCommand(Matcher matcher) {
        this.index = Integer.parseInt(matcher.group("index")) - 1;
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws InvalidIndexException {
        taskListStorage.markAsUndone(index);
    }

}
