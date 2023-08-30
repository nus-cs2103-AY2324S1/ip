package duke.commands;

import duke.TaskList;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    private final String args;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String[] tokens = args.split(" /by ", 2);
        String description = tokens[0];

        if (description.isEmpty()) {
            throw new CommandException("Deadline description cannot be empty!");
        }
        if (tokens.length != 2) {
            throw new CommandException("Deadline due date cannot be empty!");
        }

        String by = tokens[1];
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        return new CommandResult(true, "Got it. I've added this task:", deadline.toString(), String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }
}
