package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class UpdateCommand extends Command {
    /** The index of task to be marked*/
    private final int index;

    private final String updateField;

    private final String updateValue;

    /**
     * Constructs UpdateCommand using the index, update field and update value of task.
     *
     * @param index the index of the task to be marked.
     */
    public UpdateCommand(int index, String updateField, String updateValue) {
        this.index = index;
        this.updateField = updateField;
        this.updateValue = updateValue;
    }

    /**
     * Command that executes the updating of task.
     *
     * @param tasks Contains all current tasks.
     * @param ui Ui for interacting with user.
     * @param storage Storage that the data file is stored in.
     * @return A String representing the output message.
     * @throws NobitaException If the task is not inside tasklist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        if (tasks.checkIndexWithinRange(index)) {
            throw new NobitaException("Selected task number not in list");
        }
        assert index >= 0 && index < tasks.getTasksSize() : "Index should be within tasks range";
        Task task = tasks.updateTask(index, updateField, updateValue);
        String outputMessage = "OK, I've update this task:\n" + task;
        ui.showMessage(outputMessage);
        return outputMessage;
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
