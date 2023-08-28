package duke.command;

import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.data.task.Todo;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
        ui.showMessage(
                "Got it. I've added this task:",
                "\t" + toAdd,
                "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}
