package rayshawn.chatbot.commands;

import java.util.ArrayList;
import java.util.List;

import rayshawn.chatbot.exceptions.ChatBotException;
import rayshawn.chatbot.tasks.Task;

/**
 * Find and list tasks with a certain keyword to the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the specific keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " homework";

    private static final String MESSAGE_FAILED = "You have no tasks with the keyword: ";
    private final String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param keyword to be searched for
     */
    public FindCommand(String keyword) throws ChatBotException {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        final List<Task> foundTasks = getTasksWithNameContaining(keyword);
        if (foundTasks.size() == 0) {
            return new CommandResult(MESSAGE_FAILED + keyword);
        }
        return new CommandResult(getTaskListCount(foundTasks), foundTasks);
    }

    private List<Task> getTasksWithNameContaining(String keyword) {
        final List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
