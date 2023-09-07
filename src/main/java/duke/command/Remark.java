package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Represents a command to mark or unmark a task as done.
 * This command allows marking a task as done or unmarking a task.
 */
public class Remark extends Command {

    /** To indicate this command is for marking or unmarking */
    private final int state;

    /**
     * Constructs a Remark command with the given input string and state.
     *
     * @param s The input string containing the task number.
     * @param state The state indicating whether to mark (0) or unmark (1) the task.
     */
    public Remark(String s, int state) {
        super(s);
        this.state = state;
    }

    /**
     * Executes the Remark command.
     * Marks or unmarks a task as done based on the specified state,
     * updates the user interface, and updates the storage.
     *
     * @param lst The task list containing the tasks.
     * @param io The user interface handling input and output.
     * @param storage The storage handler for storing task data.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList lst, UI io, Storage storage) throws DukeException {
        try {
            int index = CommonMethods.getIndex(s);
            if (state == 0) {
                Task t = lst.unmark(index);
                storage.changeFile(lst);
                return io.mark(t);
            } else {
                Task t = lst.mark(index);
                storage.changeFile(lst);
                return io.unmark(t);
            }
        } catch (IOException ignored) {
            return ignored.getMessage();
        }
    }
}
