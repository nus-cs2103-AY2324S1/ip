package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Time;
import duke.Ui;
import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add a task to the task list. A <code>AddCommand</code>
 * object corresponds to an executable command when the user wants to add a task
 * to the task list.
 */
public class AddCommand extends Command {

    private Keyword key;
    private String commandBody;

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
     * @param ui the user interface to print messages to the user
     * @param storage the storage to save the task list to
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task;
        String err = String.format("OOPS!!! The command for %s task is invalid.", key.getKeyword());
        switch(key) {
        case TODO:
            task = new Todo(commandBody);
            break;

        case DEADLINE:
            String[] deadlineTask = commandBody.split(" /by ");
            if (deadlineTask.length != 2) {
                throw new DeadlineException(err);
            }
            task = new Deadline(deadlineTask[0], Time.parseDateTime(deadlineTask[1]));
            break;

        default: // equivalent to case EVENT
            String[] eventTask = commandBody.split(" /from ");
            if (eventTask.length != 2) {
                throw new EventException(err);
            }
            String[] dates = eventTask[1].split(" /to ");
            if (dates.length != 2) {
                throw new EventException(err);
            }
            task = new Event(eventTask[0], Time.parseDateTime(dates[0]), Time.parseDateTime(dates[1]));
            break;
        }
        taskList.addTask(task, ui);
        storage.appendFile(task.fileFormat());
    }
}
