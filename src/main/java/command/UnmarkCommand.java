package command;

import duke.Storage;
import duke.TaskList;
import exceptions.DukeException;

/**
 * Represents a command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the task index to unmark.
     *
     * @param response The full command string containing the task index.
     */
    public UnmarkCommand(int response) {
        super(false);
        this.index = response;
    }

    /**
     * Executes the unmark command, unmarking the task and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler for reading/writing tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            taskList.unmarkTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again naughty boy!");
        }
        storage.writeListToFile(taskList);
        String s = String.format("Kinky! I have marked this task as undone. \n %s",
                taskList.getTaskInString(index));
        return s;
    }
}


