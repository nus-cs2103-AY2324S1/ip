package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Deadline that adds a Deadline to the list.
 *
 * @author Joseph Oliver Lim
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a DeadlineCommand with a specified description and date.
     *
     * @param description A string describing the deadline task.
     * @param by A string describing the deadline date.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws DukeException If the deadline date is of invalid format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = new Deadline(this.description, this.by);
            tasks.addTask(task);
            return ui.addTask(task, tasks.getCountTasks());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }
}
