package duke.command;

import java.io.IOException;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * The MarkCommand class represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a new MarkCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public MarkCommand(String command) {
        super(command);
    }

    /**
     * Executes the mark command, marking a task as done.
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
        task.markDone();
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to write to file");
        }
        ui.markDone(task);
    }
}
