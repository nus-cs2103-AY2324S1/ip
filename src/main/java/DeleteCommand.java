import java.io.IOException;

/**
 * Represents the delete command
 */
public class DeleteCommand extends Command {

    /**
     * the index of the task being deleted
     */
    private int index;

    /**
     * Constructor for the MarkCommand class
     *
     * @param index - the index of the task being deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        Command.validateIndex(this.index, taskList.length());
        Task removed = taskList.remove(index);
        try {
            if (!storage.rewriteAll(taskList.getAllTask())) {
                ui.unexpectedError("not all tasks were successfully written, please contact my mother :( ");
            }
        } catch (IOException e) {
            ui.unexpectedError("error when writing to storage: " + e.getMessage());
        }
        ui.println("Quack! I have removed this task:");
        ui.println(removed.toString());
        ui.println("Quack! Quack is currently remembering " + taskList.length() + " tasks.");

    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    boolean isExit() {
        return false;
    }
}
