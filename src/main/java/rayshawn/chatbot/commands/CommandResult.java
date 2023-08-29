package rayshawn.chatbot.commands;

import java.util.List;
import java.util.Optional;

import rayshawn.chatbot.tasks.Task;
public class CommandResult {
    private final String feedbackToUser;
    private final List<Task> tasks;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = null;
    }

    public CommandResult(String feedbackToUser, List<Task> tasks) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = tasks;
    }

    public String getFeedbackToUser() {
        return this.feedbackToUser;
    }

    public Optional<List<Task>> getTaskList() {
        return Optional.ofNullable(this.tasks);
    }
}
