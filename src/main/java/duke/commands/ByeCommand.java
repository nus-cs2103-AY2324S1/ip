package duke.commands;

import duke.TaskList;

public class ByeCommand extends Command {
    @Override
    public boolean shouldExit() {
        return true;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        return new CommandResult("Bye. Hope to see you again soon!");
    }
}
