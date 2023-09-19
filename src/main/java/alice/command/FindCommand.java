package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private final String[] keywords; // The keyword to search for (case-insensitive).

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keywords The keywords to search for.
     */
    public FindCommand(String... keywords) {
        this.keywords = keywords;
    }

    /**
     * Constructs a FindCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public FindCommand(String argument) throws DukeException {
        try {
            this.keywords = argument.split(" ");
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            for (String keyword : this.keywords) {
                if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredTasks.add(task);
                    break;
                }
            }
        }
        return ui.showFilteredTaskList(filteredTasks);
    }
}
