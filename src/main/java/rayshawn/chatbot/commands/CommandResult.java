package rayshawn.chatbot.commands;

import java.util.List;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
import rayshawn.chatbot.tasks.Task;
public class CommandResult {
    public final String feedbackToUser;
    private final List<Task> tasks;

    /**
     * Constructor for CommandResult.
     *
     * @param feedbackToUser message to be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = null;
    }

    /**
     * Constructor for CommandResult.
     *
     * @param feedbackToUser message to be shown to user
     * @param tasks list of task that was produced by the command
     */
    public CommandResult(String feedbackToUser, List<Task> tasks) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = tasks;
    }

    /**
     * Returns a list of persons relevant to the command result, if any.
     *
     * @return a list of tasks if any; null otherwise
     */
    public Optional<List<Task>> getTaskList() {
        return Optional.ofNullable(this.tasks);
    }
}
