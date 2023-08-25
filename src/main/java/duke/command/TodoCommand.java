package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.UI;

public class TodoCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "todo";
    private final String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        taskList.add(new Todo(this.arguments));
        UI.sendMessage("Got it. I've added this task:\n  " +
                taskList.get(taskList.size() - 1) +
                String.format("\nNow you have %d tasks in the list.", taskList.size()));
        storage.updateFile(taskList);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
