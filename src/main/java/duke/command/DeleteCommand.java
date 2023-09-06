package duke.command;

import java.io.IOException;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * The DeleteCommand class represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a new DeleteCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the delete command, removing a task from the list.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ");
        int key;
        try {
            key = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid key given");
        }
        if (key > tasks.getSize() || key < 1) {
            throw new DukeException("Key exceeds size of list");
        }
        Task task = tasks.get(key - 1);
        tasks.delete(key - 1);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Failure to write to file");
        }
        ui.delete(task, tasks.getSize());
    }
}
