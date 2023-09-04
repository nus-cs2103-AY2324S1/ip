package commands;

import common.DukeException;
import tasks.Deadline;
import tasks.Todo;

public class AddDeadlineCommand extends Command {
    private Deadline toAdd;
    public static final String ADD_SUCCESS = "ok... I'm adding..";

    public AddDeadlineCommand(String taskName, String deadline) throws Exception {
        this.toAdd = new Deadline(taskName, deadline);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
