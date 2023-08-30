package duke.commands;

import duke.Duke;
import duke.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        if (args.isEmpty()) {
            throw new CommandException("Missing task index!");
        }

        try {
            int idx = Integer.parseInt(args);

            if (idx < 1 || idx > Duke.tasks.size()) {
                throw new CommandException("Invalid task index!");
            }

            Task task = tasks.get(idx - 1);
            tasks.remove(idx - 1);

            return new CommandResult(true, "Noted. I've removed this task:", task.toString(), "Now you have 3 tasks in the list.");
        } catch (NumberFormatException e) {
            throw new CommandException("Task index is not a number!");
        }
    }
}
