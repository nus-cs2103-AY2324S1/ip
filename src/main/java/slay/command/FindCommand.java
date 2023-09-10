package slay.command;

import slay.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for all the tasks containing the input keyword.\n"
            + "Example: " + COMMAND_WORD + "study\n";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription();
            if (description.contains(this.keyword)) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.isEmpty()) {
            return new CommandResult("Oops, there are no relevant tasks in the list.");
        } else {
            String result = "Are you looking for these tasks?";
            for (Task task : matchedTasks) {
                result += "\n" + task.toString();
            }
            return new CommandResult(result);
        }
    }
}
