package jeo.command;

import java.time.format.DateTimeParseException;

import jeo.exception.JeoException;
import jeo.storage.Storage;
import jeo.task.Deadline;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.ui.Ui;

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
     * @param description A String describing the deadline task.
     * @param by A String describing the deadline date.
     */
    public DeadlineCommand(String description, String by) {
        super(true);
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
     * @throws JeoException If the deadline date is of invalid format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeoException {
        try {
            Task task = new Deadline(this.description, this.by);
            tasks.addTask(task);
            return ui.addTask(task, tasks.getCountTasks());
        } catch (DateTimeParseException e) {
            throw new JeoException("OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }
}
