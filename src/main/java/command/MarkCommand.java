package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public MarkCommand(String fullCommand) {
        super(false);
        String[] parts = fullCommand.split(" "); {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            this.index = taskIndex;
        }
    }

    /**
     * Executes the mark command, marking the task as done and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage handler for reading/writing tasks.
     * @param ui The user interface for displaying messages.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            taskList.markTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again!");
        }
        storage.writeListToFile(taskList);
        ui.showMarkTaskMessage(taskList.getTaskInString(index), true);
        }
    }


