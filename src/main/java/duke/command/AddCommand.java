package duke.command;

import java.time.LocalDateTime;

import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Keyword;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Time;
import duke.util.Ui;

/**
 * Represents a command to add a task to the task list. A <code>AddCommand</code>
 * object corresponds to an executable command when the user wants to add a task
 * to the task list.
 */
public class AddCommand extends Command {

    private final Keyword key;
    private final String commandBody;

    /**
     * Constructs a <code>AddCommand</code> object.
     *
     * @param key the type of task to be added
     * @param commandBody the body of the command
     */
    public AddCommand(Keyword key, String commandBody) {
        super(false);
        this.key = key;
        this.commandBody = commandBody;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param taskList the task list to which the task is added
     * @param ui       the user interface to print messages to the user
     * @param storage  the storage to save the task list to
     * @throws DukeException if the command is invalid
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        Task task;
        String err = String.format("OOPS!!! The command for %s task is invalid.", key.getKeyword());
        switch(key) {
        case TODO:
            task = new Todo(commandBody);
            break;

        case DEADLINE:
            task = parseDeadline(err);
            break;

        case EVENT:
            task = parseEvent(err);
            break;

        default:
            throw new DukeException("Sorry, the command is invalid.");
        }
        Response respond = taskList.addTask(task, ui);
        storage.saveTasks(taskList.saveTaskList());
        return respond;
    }

    /**
     * Parses the command body to create a <code>Deadline</code> object.
     *
     * @param err the error message to be displayed if the command body is invalid
     * @return the <code>Deadline</code> object created
     * @throws DukeException if the command body is invalid
     */
    private Task parseDeadline(String err) throws DukeException {
        String[] deadlineTask = commandBody.split(" /by ");
        if (deadlineTask.length != 2) {
            throw new DeadlineException(err);
        }
        return new Deadline(deadlineTask[0], Time.parseDateTime(deadlineTask[1]));
    }

    /**
     * Parses the command body to create an <code>Event</code> object.
     *
     * @param err the error message to be displayed if the command body is invalid
     * @return the <code>Event</code> object created
     * @throws DukeException if the command body is invalid
     */
    private Task parseEvent(String err) throws DukeException {
        String[] eventTask = commandBody.split(" /from ");
        if (eventTask.length != 2) {
            throw new EventException(err);
        }
        String[] dates = eventTask[1].split(" /to ");
        if (dates.length != 2) {
            throw new EventException(err);
        }
        LocalDateTime start = Time.parseDateTime(dates[0]);
        LocalDateTime end = Time.parseDateTime(dates[1]);
        if (start.isAfter(end)) {
            throw new DukeException("OOPS!!! The start time cannot be after the end time.");
        }
        return new Event(eventTask[0], start, end);
    }
}
