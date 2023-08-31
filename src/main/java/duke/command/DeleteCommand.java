package duke.command;



// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToDelete = taskList.getTask(index);
            taskList.deleteTask(taskToDelete);
            ui.displayDeleteTask(taskToDelete, taskList.numTasks());
            storage.saveTasks(taskList.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Invalid task index.");
        }
    }
}