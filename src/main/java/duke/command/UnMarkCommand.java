package duke.command;

import java.io.IOException;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * The UnMarkCommand class represents a command to mark a task as undone.
 */
public class UnMarkCommand extends Command {
    /**
     * Constructs a new UnMarkCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public UnMarkCommand(String command) {
        super(command);
    }

    /**
     * Executes the unmark command, marking a task as undone.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public String execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ", 2);
        assert inputs.length == 2;
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
        task.unmark();
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to write to file");
        }
        return ui.unmark(task);
    }
}
