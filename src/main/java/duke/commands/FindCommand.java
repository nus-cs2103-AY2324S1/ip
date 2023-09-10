package duke.commands;

import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand implements Command {

    private String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskListStorage taskListStorage)
            throws InvalidIndexException, MissingDescriptionException, IncorrectCommandFormatException {
        return taskListStorage.findTasks(keyword);
    }

}
