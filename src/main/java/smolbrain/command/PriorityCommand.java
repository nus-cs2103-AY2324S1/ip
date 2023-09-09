package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

/**
 * Handles setting priority level of tasks.
 */
public class PriorityCommand implements Command {

    private int id;
    private int priorityLevel;
    private boolean isLoading;

    /**
     * Creates the command.
     * @param id Array id of task to set priority.
     * @param level Priority level in this command.
     */
    public PriorityCommand(int id, int level) {
        this.id = id;
        this.priorityLevel = level;
    }

    /**
     * Executes ths command.
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
        tasks.setTaskPriority(id, priorityLevel);
        ui.showMessage("OK, I've marked this task with priority level " + priorityLevel + ": \n" + tasks.getTask(id));
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
