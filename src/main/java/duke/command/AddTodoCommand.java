package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.data.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to add a new todo to the list of tasks.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private final Todo toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(toAdd);
        ui.showMessage(
                "Got it. I've added this task:",
                "\t" + toAdd,
                "Now you have " + tasks.size() + " tasks in the list."
        );
        storage.save(tasks);
    }
}
