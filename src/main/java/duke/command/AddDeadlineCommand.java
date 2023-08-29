package duke.command;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Deadline;

/**
 * The AddDeadlineCommand class represents a command to add a new Deadline task to the list.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Constructs a new AddDeadlineCommand object with the specified command.
     *
     * @param command The input command string.
     */
    public AddDeadlineCommand(String command) {
        super(command);
    }

    /**
     * Executes the add Deadline command, adding a new Deadline task to the list.
     *
     * @param tasks   The DukeList containing the list of tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for file storage.
     * @throws DukeException If there is an error during execution.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = this.command.split(" ", 2);
        Deadline deadline;
        try {
            String[] by = inputs[1].split("/by", 2);
            deadline = new Deadline(by[0], by[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid Deadline format");
        }
        tasks.add(deadline);
        ui.addToList(deadline, tasks.getSize());
    }
}
