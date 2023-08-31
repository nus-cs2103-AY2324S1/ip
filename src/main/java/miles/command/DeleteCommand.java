package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.Task;

public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor to create a new delete command.
     * 
     * @param input
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Return task number from user input.
     * 
     * @param input user input
     * @return      task number 
     */
    public int getTaskNumber(String input) {
        return super.getTaskNumber("delete", input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskNum = getTaskNumber(this.input);
        Task deletedTask = taskList.deleteTask(taskNum - 1);
        storage.saveFile(taskList);
        int n = taskList.getSize();
        ui.printDeletedTask(deletedTask, n);
    }
}
