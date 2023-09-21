package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.Task;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class DeleteCommand extends Command {

    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task deletedTask = taskList.get(taskIndex);
            taskList.removeTask(taskIndex);
            ui.showDeleteTaskMessage(taskList, deletedTask);
            return ui.getOutput();
        } else {
            throw new AvalonException("Invalid task number to be deleted.");
        }
    }
}
