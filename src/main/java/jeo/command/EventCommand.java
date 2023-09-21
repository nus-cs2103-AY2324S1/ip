package jeo.command;

import java.time.format.DateTimeParseException;

import jeo.exception.JeoException;
import jeo.storage.Storage;
import jeo.task.Event;
import jeo.task.Task;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command Event that adds an Event to the list.
 *
 * @author Joseph Oliver Lim
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with a specified description, start date and end date.
     *
     * @param description A string describing the event name.
     * @param from A string describing the start date.
     * @param to A string describing the end date.
     */
    public EventCommand(String description, String from, String to) {
        super(true);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the EventCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws JeoException If the event start date or end date is of invalid format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeoException {
        try {
            Task task = new Event(this.description, this.from, this.to);
            tasks.addTask(task);
            return ui.addTask(task, tasks.getCountTasks());
        } catch (DateTimeParseException e) {
            throw new JeoException("OOPS!!! Invalid date format. Please type dates in the format yyyy-mm-dd");
        }
    }
}
