package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class for the command for deleting task from list based on user input.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task in the taskList to be deleted.
     */
    private final int index;

    /**
     * The task to be deleted.
     */
    private Task deletedTask;

    /**
     * The constructor for DeleteCommand
     * @param index The index of the task in the taskList to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        String result = this.printCommand(taskList);
        this.deletedTask = taskList.getTask(this.index - 1);
        taskList.removeTask(this.index - 1);
        if (write) {
            storage.writeData(taskList.convertToFileContent());
            commandList.addCommand(this);
            storage.previousCommandsWriter(commandList.convertToFileContent());
        }
        return result;
    }

    @Override
    public String printCommand(TaskList taskList) {
        Task temp = taskList.getTask(this.index - 1);
        return "JonBird:\n\tNoted. I've removed this task:\n"
                + "\t\t" + temp.printTask()
                + "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    /**
     * Returns the task that is deleted by the command
     */
    public Task getTask() {
        return this.deletedTask;
    }

    /**
     * Returns the index of the task that is deleted by the command
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Set the task for the delete command
     */
    public void taskSetter(Task task) {
        this.deletedTask = task;
    }
}
