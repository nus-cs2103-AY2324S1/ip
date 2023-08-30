package duke.commands;

import duke.TaskList;
import duke.tasks.Task;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        if (args.isEmpty()) {
            throw new CommandException("Missing task index!");
        }

        try {
            int idx = Integer.parseInt(args);

            if (idx < 1 || idx > tasks.size()) {
                throw new CommandException("Invalid task index!");
            }

            Task task = tasks.get(idx - 1);

            if (task.isDone()) {
                throw new CommandException("Task has already been done!");
            }

            task.markAsDone();
            return new CommandResult(true, "Nice! I've marked this task as done:", task.toString());
        } catch (NumberFormatException e) {
            throw new CommandException("Task index is not a number!");
        }
    }
}
