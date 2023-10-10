package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;

import java.util.List;

/**
 * Represents a command that finds tasks using a keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String MESSAGE = "Here are the matching tasks in your list:\n%1$s";
    private final String keyword;

    /**
     * Constructs a FindCommand instance containing a keyword that will be used to search for tasks.
     *
     * @param keyword The keyword used to search for tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        List<Task> matchingTasks = tasks.find(this.keyword);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task matchingTask = matchingTasks.get(i);
            sb.append(i + 1).append(".");
            sb.append(matchingTask);
            sb.append(System.lineSeparator());
        }

        return new CommandResult(String.format(MESSAGE, sb));
    }
}
