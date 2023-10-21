package command;

import duke.Storage;
import duke.TaskList;
import exceptions.DukeException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the task index to mark.
     *
     * @param fullCommand The full command string containing the task index.
     */
    public MarkCommand(int response) {
        super(false);
        this.index = response;
    }

    /**
     * Executes the mark command, marking the task as done and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler for reading/writing tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            taskList.markTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again naughty boy!");
        }
        storage.writeListToFile(taskList);
        String s = String.format("Good job dirty boy! I have marked this task as done!\n %s",
                taskList.getTaskInString(index));
        return s;
    }
}


