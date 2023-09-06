package brandon.chatbot.commands;

import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

