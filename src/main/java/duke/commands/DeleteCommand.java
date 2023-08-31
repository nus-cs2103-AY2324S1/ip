package duke.commands;

import duke.exceptions.NoIndexException;
import duke.tasks.Task;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the given full command string.
     *
     * @param fullCommand The full command string.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the delete command, removing a task from the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     * @throws NoIndexException If the provided index is invalid or missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoIndexException {
        String desc = fullCommand.replaceAll("[^0-9]", "");
        if (desc.equals("")) {
            throw new NoIndexException("No Index");
        }
        int index = Integer.parseInt(desc);

        if (tasks.size() < index || index < 1) {
            throw new NoIndexException(Integer.toString(index));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.showDeleteMessage(task, tasks.size());
    }
}
