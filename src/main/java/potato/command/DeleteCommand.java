package potato.command;

import java.io.IOException;

import potato.PotatoException;
import potato.Storage;
import potato.TaskList;
import potato.Ui;

/**
 * The DeleteCommand class represents a command for deleting a task in the task list.
 * It extends the Command class and specifies the behavior of executing a "delete" command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand object with the provided user input.
     *
     * @param input The user input specifying the index of the task to be deleted.
     */
    public DeleteCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    /**
     * Executes the delete command, which deletes a task from the task list.
     *
     * @param tasks   The TaskList containing the current list of tasks.
     * @param storage The Storage utility for saving task changes.
     * @return A message indicating the result of deleting the task.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            return tasks.delete(input, storage);
        } catch (PotatoException e) {
            return e.getMessage();
        }
    }
}
