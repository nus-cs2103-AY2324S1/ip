package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Task;

/**
 * The UnmarkCommand class represents a command to unmark a task as done in the Monke application.
 * It extends the Command class.
 */
public class UnmarkCommand extends Command {
    /** The command word for parser to recognize this command. */
    public static final String COMMAND_WORD = "unmark";

    /** The task number to unmark as undone. */
    private String taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The task number to unmark as undone.
     */
    public UnmarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand to unmark a task as undone.
     *
     * @param ui The user interface.
     * @param storage The storage to read and write data.
     * @param tasks The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Task task = tasks.getTask(this.taskNumber);
        task.unmark();
        storage.saveData(tasks);

        ui.print("Ooga booga! I've marked this task as undone:");
        ui.print("\t" + task);
    }
}
