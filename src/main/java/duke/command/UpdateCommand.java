package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Represents the command of updating a task in the task list.
 */
public class UpdateCommand extends Command {
    /** Index of the task to be updated. */
    protected int index;
    /** Updated description of the task. */
    protected String description;
    /** Updated first date of the task. */
    protected LocalDateTime firstDate;
    /** Updated second date of the task. */
    protected LocalDateTime secondDate;
    /** Indicates whether the firstDate is the first date of an event. */
    protected boolean isFirst;

    /**
     * Constructor for the update command if only description is to be updated.
     * 
     * @param index Index of the task to be updated.
     * @param description Description to update with.
     */
    public UpdateCommand(int index, String description) {
        this.index = index - 1;
        this.description = description;
    }

    /**
     * Constructor for the update command if the date is to be updated and if the task only has 1 date.
     * 
     * @param index Index of the task to be updated.
     * @param date Date to update with.
     */
    public UpdateCommand(int index, LocalDateTime date) {
        this.index = index - 1;
        this.firstDate = date;
    }

    /**
     * Constructor for the update command if the date is to be updated and if the task has 2 dates.
     * 
     * @param index Index of the task to be updated.
     * @param date Date to update with.
     * @param isFirst Boolean value to see if the date to be updated is the first date.
     */
    public UpdateCommand(int index, LocalDateTime date, boolean isFirst) {
        this.index = index - 1;
        this.firstDate = date;
        this.isFirst = isFirst;
    }

    /**
     * Constructor for the update command if both dates are to be updated.
     * 
     * @param index Index of the task to be updated.
     * @param firstDate First date to update with.
     * @param secondDate Second date to update with.
     */
    public UpdateCommand(int index, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.index = index - 1;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    /**
     * Constructor for the update commmand if description and a date is to be updated,
     * given that the task has 2 dates.
     * 
     * @param index Index of the task to be updated.
     * @param description Description to update with.
     * @param date Date to update with.
     * @param isFirst Boolean value to see if the date to be updated is the first date.
     */
    public UpdateCommand(int index, String description, LocalDateTime date, boolean isFirst) {
        this.index = index - 1;
        this.description = description;
        this.firstDate = date;
        this.isFirst = isFirst;
    }

    /**
     * Constructor for the update command if description and both dates are to be updated.
     * 
     * @param index Index of the task to be updated.
     * @param description Description to update with.
     * @param firstDate First date to update with.
     * @param secondDate Second date to update with.
     */
    public UpdateCommand(int index, String description, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.index = index - 1;
        this.description = description;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to change is invalid!");
        }
        Task currTask = taskList.get(this.index);
        if (currTask instanceof ToDo) {
            Command newCommand = new UpdateToDoCommand(index, description);
            newCommand.execute(taskList, ui, storage);
        }
        if (currTask instanceof Deadline) {
            if (this.description != null) {
                if (this.firstDate != null) {
                    Command newCommand = new UpdateDeadlineCommand(index, description, firstDate);
                    newCommand.execute(taskList, ui, storage);
                } else {
                    Command newCommand = new UpdateDeadlineCommand(index, description);
                    newCommand.execute(taskList, ui, storage);
                }
            } else {
                Command newCommand = new UpdateDeadlineCommand(index, firstDate);
                newCommand.execute(taskList, ui, storage);
            }
        } if (currTask instanceof Event) {
            if (this.description != null) {
                if (this.firstDate != null) {
                    if (this.secondDate != null) {
                        Command newCommand = new UpdateEventCommand(index, description, firstDate, secondDate);
                        newCommand.execute(taskList, ui, storage);
                    } else {
                        Command newCommand = new UpdateEventCommand(index, description, firstDate, isFirst);
                        newCommand.execute(taskList, ui, storage);
                    }
                } else {
                    Command newCommand = new UpdateEventCommand(index, description);
                    newCommand.execute(taskList, ui, storage);
                }
            } else {
                if (this.firstDate != null) {
                    if (this.secondDate != null) {
                        Command newCommand = new UpdateEventCommand(index, firstDate, secondDate);
                        newCommand.execute(taskList, ui, storage);
                    } else {
                        Command newCommand = new UpdateCommand(index, firstDate, isFirst);
                        newCommand.execute(taskList, ui, storage);
                    }
                }
            }
        }
    }
}
