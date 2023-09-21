package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class UnmarkCommand extends Command {

    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            taskList.get(taskIndex).markNotDone();
            ui.showUnmarkMessage(taskList, taskIndex);
            return ui.getOutput();
        } else {
            throw new AvalonException("Invalid task number to be unmarked.");
        }
    }
}
