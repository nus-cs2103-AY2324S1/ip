package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.Task;
import smolbrain.task.TaskList;

/**
 * Handles adding tasks.
 */
public class AddCommand implements Command {

    private Task task;
    private boolean loading;

    /**
     * Creates the command.
     * @param task Task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Sets the flag that chatbot is loading to true.
     */
    public void setLoading() {
        this.loading = true;
    }

    /**
     * Executes this command.
     *
     * @param tasks List of tasks of chatbot.
     * @param ui Ui manager of chatbot.
     * @param storage Storage manager of chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        if (!loading) {
            ui.showMessage("Got it. I've added this task: \n" + task);
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        }
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
}