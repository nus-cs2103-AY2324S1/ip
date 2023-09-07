package brandon.chatbot.commands;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the result of running the command.
 */
public class CommandResult {
    public final String feedbackToUser;
    private TaskList tasks;
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Constructs CommandResult instance when the command requires printing the task list.
     *
     * @param feedbackToUser is a feedback the user sees.
     * @param tasks is the instance of TaskList to be printed.
     */
    public CommandResult(String feedbackToUser, TaskList tasks) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = tasks;
    }

    /**
     * Returns Optional object containing TaskList, if there is one.
     *
     * @return Optional object containign TaskList if exists.
     */
    public Optional<TaskList> getTasks() {
        return Optional.ofNullable(tasks);
    }
}

