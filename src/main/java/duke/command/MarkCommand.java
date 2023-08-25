package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class MarkCommand extends NumberedChoiceCommand implements Command {

    private static final String commandString = "mark";
    private final String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(taskList);
        int choice = Integer.parseInt(arguments) - 1;
        taskList.get(choice).markAsDone();
        UI.sendMessage("Nice! I've marked this task as done:\n  " + taskList.get(choice));
        storage.updateFile(taskList);
    }

    private void validate(TaskList taskList) throws DukeException {
        super.validate(this.arguments);
        int choice = Integer.parseInt(arguments) - 1;
        if (choice < 0 || choice >= taskList.size()) {
            throw new DukeException("Argument Provided out of range: " + (choice + 1));
        }
    }

    @Override
    public String toString() {
        return commandString;
    }
}
