package duke.commands;

import java.util.regex.Pattern;

import duke.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^delete\\s+(?<taskNum>.+)$");

    public DeleteCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join("\n", "Invalid format for command `delete`!", "Usage: delete <TASK_NUMBER>");
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String taskNum = matcher.group("taskNum");

        if (taskNum.isEmpty()) {
            throw new CommandException("Task number cannot be empty!");
        }

        try {
            int idx = Integer.parseInt(taskNum) - 1;

            if (idx < 0 || idx >= tasks.size()) {
                throw new CommandException("Invalid task number!");
            }

            Task task = tasks.get(idx);
            tasks.remove(idx);

            return new CommandResult(
                    true,
                    "Noted. I've removed this task:",
                    task.toString(),
                    String.format(
                            "You have %d %s in your list.",
                            tasks.size(),
                            tasks.size() == 1 ? "task" : "tasks"
                    )
            );
        } catch (NumberFormatException e) {
            throw new CommandException("Task number must be a number!");
        }
    }
}
