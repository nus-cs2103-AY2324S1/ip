package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Task;

/**
 * The MarkCommand class represents a command to mark a task as done in the Monke application.
 * It extends the Command class.
 */
public class MarkCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "mark";

    /** The task number to mark as done. */
    private String taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber The task number to mark as done.
     */
    public MarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand to mark a task as done.
     *
     * @param ui      The user interface.
     * @param storage The storage to read and write data.
     * @param tasks   The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Task task = tasks.getTask(this.taskNumber);
        task.mark();
        storage.saveData(tasks);
        ui.print("Ooga booga! I've marked this task as done:");
        ui.print("\t" + task);
    }
}
