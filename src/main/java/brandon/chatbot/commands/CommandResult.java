package brandon.chatbot.commands;

import java.util.Optional;

import brandon.chatbot.tasks.TaskList;

public class CommandResult {
    public final String feedbackToUser;
    private TaskList tasks;
    public CommandResult(String feedbackToUser) {

        this.feedbackToUser = feedbackToUser;
    }

    public CommandResult(String feedbackToUser, TaskList tasks) {
        this.feedbackToUser = feedbackToUser;
        this.tasks = tasks;
    }

    public Optional<TaskList> getTasks() {
        return Optional.ofNullable(tasks);
    }
}

