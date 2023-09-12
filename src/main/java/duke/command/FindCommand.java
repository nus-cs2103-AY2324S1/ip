package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to find tasks that match the keyword provided.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private static final String COMMAND_RESPONSE = "Here are the matching tasks in your list:\n";

    private String keyword;

    /**
     * Returns an instance of {@code FindCommand} with the given keyword.
     *
     * @param keyword The specified keyword to filter the list of tasks by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        assert !keyword.trim().isEmpty() : "The keyword should be non-empty";
        boolean containsWhitespace = keyword.matches(".*\\s.*");
        assert !containsWhitespace : "The keyword should be a single word";
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList filteredTasks = tasks.filter(keyword);
        return COMMAND_RESPONSE + filteredTasks.getFormattedList();
    }
}
