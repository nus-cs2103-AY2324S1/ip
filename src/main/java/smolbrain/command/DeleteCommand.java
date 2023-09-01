package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.Task;
import smolbrain.task.TaskList;

/**
 * Handles deleting tasks.
 */
public class DeleteCommand implements Command {

    private int id;
    private boolean loading;

    /**
     * Creates the command.
     * @param id Array id of task to delete.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes this command.
     *
     * @param tasks List of tasks of chatbot.
     * @param ui Ui manager of chatbot.
     * @param storage Storage manager of chatbot.
     * @throws InvalidRangeException If the id given by user for array access is out of bound.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException {
        if (this.id < 0 || this.id >= tasks.getSize()) {
            throw new InvalidRangeException();
        }
        Task task = tasks.getTask(id);
        tasks.deleteTask(id);
        ui.showMessage("Noted. I've removed this task: \n" + task);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        tasks.updateTasks(storage);
    }

    /**
     * Indicates if this command causes chatbot to exit.
     *
     * @return Boolean value if this command exits the chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    @Override
    public void setLoading() {
        this.loading = true;
    }

}
