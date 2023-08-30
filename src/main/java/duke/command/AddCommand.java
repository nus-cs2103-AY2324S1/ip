package duke.command;

import duke.exception.DukeException;
import duke.exception.DeadlineException;
import duke.exception.EventException;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Keyword;
import duke.Time;

public class AddCommand extends Command {

    private Keyword key;
    private String commandBody;

    public AddCommand(Keyword key, String commandBody) {
        super(false);
        this.key = key;
        this.commandBody = commandBody;
    }

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
            try {
                task = new Deadline(deadlineTask[0], Time.parseDateTime(deadlineTask[1]));
            } catch (DukeException e) {
                throw new DeadlineException(err);
            }
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
            try {
                task = new Event(eventTask[0], Time.parseDateTime(dates[0]), Time.parseDateTime(dates[1]));
            } catch (DukeException e) {
                throw new EventException(err);
            }
            break;
        }
        taskList.addTask(task, ui);
        storage.appendFile(task.fileFormat());
    }
}
