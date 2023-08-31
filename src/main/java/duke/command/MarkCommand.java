package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToMark = taskList.getTask(taskIndex);
            taskToMark.markAsDone();
            ui.displayMarked(taskToMark);
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Invalid task index.");
        }
    }
}









