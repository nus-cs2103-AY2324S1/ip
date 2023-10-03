package duke.commands.taskcommands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.commands.TaskCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Todo;

/**
 * Represents a command that adds a todo task to the task list.
 */
public class TodoCommand extends TaskCommand {
    /**
     * Creates a TodoCommand object.
     *
     * @param matcher The matcher object used to extract the description.
     * @throws MissingDescriptionException If the description is missing.
     */
    public TodoCommand(Matcher matcher) throws MissingDescriptionException {
        super(matcher);
    }

    @Override
    public String execute(TaskListStorage taskListStorage)
            throws MissingDescriptionException, IncorrectCommandFormatException {
        return taskListStorage.addTask(new Todo(this.description));
    }
}
