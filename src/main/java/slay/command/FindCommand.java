package slay.command;

import slay.exception.EmptyArgumentException;
import slay.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for all the tasks containing the input keyword.\n"
            + "Example: " + COMMAND_WORD + "study\n";

    public static final String MESSAGE_TASK_NOT_FOUND = "Sorry, there are no relevant tasks in the list.";
    public static final String MESSAGE_FIND_TASK_SUCCESS_PREFIX = "Are you looking for these tasks?";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            int visibleIndex = i + 1;
            Task task = taskList.getTask(visibleIndex);
            String description = task.getDescription();
            if (description.contains(this.keyword)) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.isEmpty()) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } else {
            String tasksFound = "";
            for (Task task : matchedTasks) {
                tasksFound += "\n" + task.toString();
            }
            return new CommandResult(MESSAGE_FIND_TASK_SUCCESS_PREFIX + tasksFound);
        }
    }
}
