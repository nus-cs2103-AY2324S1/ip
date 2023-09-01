package monke.commands;

import java.time.LocalDateTime;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Deadline;

/**
 * The DeadlineCommand class represents a command to add a deadline task.
 * It extends the Command class.
 */
public class DeadlineCommand extends Command {
    /** The command word for parser to recognise this command. */
    public static final String COMMAND_WORD = "deadline";

    /** The description of the deadline task. */
    private String description;

    /** The deadline date and time of the task. */
    private LocalDateTime date;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param date        The deadline date and time of the task.
     */
    public DeadlineCommand(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Executes the DeadlineCommand to add a deadline task to the task list.
     *
     * @param ui      The user interface.
     * @param storage The storage to read and write data.
     * @param tasks   The list of tasks.
     * @throws MonkeException If data cannot be saved in the storage.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Deadline deadline = new Deadline(this.description, this.date);
        tasks.add(deadline);
        storage.saveData(tasks);
        ui.showAddTask(deadline, tasks.size());
    }
}
