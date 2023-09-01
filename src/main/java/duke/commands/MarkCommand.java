package duke.commands;

import java.util.regex.Pattern;

import duke.TaskList;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^mark\\s+(?<taskNum>.+)$");

    public MarkCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join("\n", "Invalid format for command `mark`!", "Usage: mark <TASK_NUMBER>");
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

            if (task.isDone()) {
                throw new CommandException("Task has already been done!");
            }

            task.markAsDone();
            return new CommandResult(true, "Nice! I've marked this task as done:", task.toString());
        } catch (NumberFormatException e) {
            throw new CommandException("Task number must be a number!");
        }
    }
}
