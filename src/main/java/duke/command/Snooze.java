package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.io.IOException;

/**
 * Represents a command to snooze a task.
 * This command snoozes a task by a specified number of days.
 */
public class Snooze extends Command{
    /**
     * Constructs a Snooze command with the given input string.
     *
     * @param str The input string.
     */
    public Snooze(String str) {
        super(str);
    }

    /**
     * Executes the Snooze command.
     * Snoozes a task by a specified number of days.
     *
     * @param lst The task list.
     * @param ui The user interface handling input and output.
     * @param storage The storage handler.
     * @throws DukeException If there is an error while snoozing the task.
     */
    @Override
    public String execute(TaskList lst, UI ui, Storage storage) throws DukeException {
        try {
            int index = CommonMethods.getIndex(str);
            assert index >= 0 : "Index should be non-negative";

            Task t = lst.snooze(index);
            assert t != null : "Task should not be null";

            storage.changeFile(lst);
            return ui.snooze(t);
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}
