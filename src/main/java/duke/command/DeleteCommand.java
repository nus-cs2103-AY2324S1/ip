package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public class DeleteCommand extends NumberedChoiceCommand implements Command {
    private static final String commandString = "mark";
    private final String arguments;

    public DeleteCommand(String arguments) {
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
        UI.sendMessage("Noted. I've removed this task:\n  " +
                taskList.get(choice) +
                String.format("\nNow you have %d tasks in the list.", taskList.size() - 1));
        taskList.remove(choice);
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
