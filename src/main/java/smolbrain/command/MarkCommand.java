package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

/**
 * Handles marking tasks.
 */
public class MarkCommand implements Command {

    private int id;
    private boolean loading;

    /**
     * Creates the command.
     * @param id Array id of task to mark.
     */
    public MarkCommand(int id) {
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
        tasks.markTask(id);
        ui.showMessage("Nice! I've marked this task as done: \n" + tasks.getTask(id));
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
