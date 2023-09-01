package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.DukeException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskToDeleteIndex;

    /**
     * Constructs a DeleteCommand object with the task index to delete.
     *
     * @param fullCommand The full command string containing the task index.
     */
    public DeleteCommand(String fullCommand) {
        super(false);
        String[] parts = fullCommand.split(" ");
        this.taskToDeleteIndex = Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Executes the delete command, removing the task from the task list and updating storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage handler for reading/writing tasks.
     * @param ui The user interface for displaying messages.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            taskList.deleteTask(this.taskToDeleteIndex);
        } catch (IndexOutOfBoundsException e) {
        throw new DukeException("This task does not exist! Try again!");
    }
        storage.writeListToFile(taskList);
        taskList.printTaskListInString();
    }
}
