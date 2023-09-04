package commands;

import common.DukeException;
import common.DukeUnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

public class AddTodoCommand extends Command {
    private Todo toAdd;
    public static final String ADD_SUCCESS = "ok... I'm adding..";

    public AddTodoCommand(String taskName) throws DukeException {
        this.toAdd = new Todo(taskName);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
