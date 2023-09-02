package duke.commands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.exceptions.InvalidIndexException;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand implements Command {

    private int index;

    /**
     * Creates a MarkCommand object.
     * 
     * @param matcher The matcher object that contains the user input matched to the mark regex.
     */
    public MarkCommand(Matcher matcher) {
        this.index = Integer.parseInt(matcher.group("index")) - 1;
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws InvalidIndexException {
        taskListStorage.markAsDone(index);
    }

}
