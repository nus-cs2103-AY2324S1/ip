package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;
import spot.task.ToDo;

/**
 * Represents a command to add a new todo.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructs a new AddToDoCommand object.
     *
     * @param description Description of the ToDo.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddToDoCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        ToDo t = tasks.addTodo(description);
        ui.setAdd(tasks, t);
    }

    /**
     * Checks if the AddToDoCommand is an ExitCommand.
     *
     * @return Boolean representing whether the AddToDoCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
