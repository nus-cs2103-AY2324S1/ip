package duke.commands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.exceptions.InvalidIndexException;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand implements Command{

    private int index;

    /**
     * Creates a DeleteCommand object.
     * 
     * @param matcher The matcher object that contains the user input matched to the delete regex.
     */
    public DeleteCommand(Matcher matcher) {
        this.index = Integer.parseInt(matcher.group("index")) - 1;
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws InvalidIndexException {
        taskListStorage.deleteTask(index);
    }

}