package rayshawn.chatbot.commands;

import java.util.List;
import java.util.Optional;

import rayshawn.chatbot.tasks.Task;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    // Adapted from
    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/CommandResult.java

    public final String feedbackToUser;
    private final List<Task> tasks;

    /**
     * Constructor for CommandResult.
     *
     * @param feedbackToUser message to be shown to the user
     */
    public CommandResult(String feedbackToUser) {
        assert feedbackToUser != null : "Feedback message should not be null";
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
        assert feedbackToUser != null : "Feedback message should not be null";
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
