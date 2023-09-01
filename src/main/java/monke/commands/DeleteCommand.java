package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class.
 */
public class DeleteCommand extends Command {
    /** The command word for parser to recognise this command. */
    public static final String COMMAND_WORD = "delete";

    /** The number corresponding to the task */
    private String taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The task number of the task to be deleted.
     */
    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand to delete a task from the task list.
     *
     * @param ui The user interface.
     * @param storage The storage to read and write data.
     * @param tasks The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        tasks.delete(this.taskNumber);
        storage.saveData(tasks);

        ui.print("Noted. I've removed this task:");
        ui.print("\t" + tasks.getTask(this.taskNumber));
        ui.print("Now you have " + tasks.size() + " tasks in the list.");
    }
}
