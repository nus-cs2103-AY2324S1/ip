package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

/**
 * Represents a command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the task index to unmark.
     *
     * @param fullCommand The full command string containing the task index.
     */
    public UnmarkCommand(String fullCommand) {
        super(false);
        String[] parts = fullCommand.split(" "); {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            this.index = taskIndex;
        }
    }

    /**
     * Executes the unmark command, unmarking the task and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage handler for reading/writing tasks.
     * @param ui The user interface for displaying messages.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            taskList.unmarkTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again!");
        }
        storage.writeListToFile(taskList);
        ui.showMarkTaskMessage(taskList.getTaskInString(index), false);
        }
    }


