package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to create a Deadline object.
 */
public class DeadlineCommand extends Command {
    /** Represents a Deadline object. */
    private Deadline deadline;

    /**
     * Constructs the DeadlineCommand.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public DeadlineCommand(String input) throws DukeException {
        if (input == null || input.isEmpty()) {
            // Catches empty description, but allows empty description with a deadline.
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!input.contains("/by")) {
            // No by date.
            throw new DukeException(" ☹ OOPS!!! By when?");
        }
        String[] tokens = input.split("/by");
        try {
            this.deadline = new Deadline(tokens[0].strip(), tokens[1].strip());
        } catch (ArrayIndexOutOfBoundsException e) {
            // Empty /by clause.
            throw new DukeException("Nice try, did you leave your <by> empty?");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(deadline);
        ui.showMessage(String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", deadline, taskList.getListSize()));
        storage.appendFile(deadline);
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @return A String message.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(deadline);
        storage.appendFile(deadline);
        return String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", deadline, taskList.getListSize());
    }
}
