package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class UnmarkCommand extends Command {

    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task tasktoUnmark = taskList.getTask(taskIndex);
            tasktoUnmark.markAsNotDone();
            ui.displayUnmarked(tasktoUnmark);
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Invalid task index.");
        }
    }
}