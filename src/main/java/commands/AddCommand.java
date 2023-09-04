package commands;

import functions.*;
import java.io.IOException;
import java.time.LocalDateTime;
import tasks.*;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    protected String desc;
    protected LocalDateTime first;
    protected LocalDateTime second;

    /**
     * Constructs an AddCommand with a description.
     *
     * @param desc The description of the task to be added.
     */
    public AddCommand(String desc) {
        this.desc = desc;
        this.first = null;
        this.second = null;
    }

    /**
     * Constructs an AddCommand with a description and a specific date.
     *
     * @param desc The description of the task to be added.
     * @param date The specific date associated with the task.
     */
    public AddCommand(String desc, LocalDateTime date) {
        this.desc = desc;
        this.first = date;
        this.second = null;
    }

    /**
     * Constructs an AddCommand with a description and start and end dates.
     *
     * @param desc  The description of the task to be added.
     * @param start The start date of the task.
     * @param end   The end date of the task.
     */
    public AddCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.first = start;
        this.second = end;
    }

    /**
     * Executes the AddCommand to create a task, display a message, and save changes.
     *
     * @param tasks   The TaskList to add the task to.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface for saving data.
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.createTask(this.desc, this.first, this.second);
        ui.showTaskMsg(t, tasks);
        storage.saveFiles(tasks.showList());
    }
}
