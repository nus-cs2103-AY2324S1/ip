package rayshawn.chatbot.commands;

import rayshawn.chatbot.tasks.Task;

import java.util.List;

public class CommandResult {
    public final String feedbackToUser;
    private final List<Task> tasks;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = null;
    }

    public CommandResult(String feedbackToUser, List<Task> tasks) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = tasks;
    }
}
