package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

/**
 * Handles unmarking tasks.
 */
public class UnmarkCommand implements Command {

    private int id;
    private boolean isLoading;

    /**
     * Creates the command.
     * @param id Array id of task to unmark.
     */
    public UnmarkCommand(int id) {
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
        tasks.unmarkTask(id);
        ui.showMessage("OK, I've marked this task as not done yet: \n" + tasks.getTask(id));
        tasks.updateTasks(storage);
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    @Override
    public void setLoading() {
        this.isLoading = true;
    }

}
