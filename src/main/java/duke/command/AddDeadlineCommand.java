package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a deadline task.
 * Inherits from the Command class.
 */
public class AddDeadlineCommand extends Command {

    /** The description of the deadline to add */
    private final String description;

    /** The deadline of the deadline to add */
    private final String by;

    /**
     * Constructs an AddDeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the AddDeadlineCommand by adding the deadline task to the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage component.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Create the task first
        Deadline deadline = new Deadline(description, by);

        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The format of a deadline should be: "
                    + "deadline DESCRIPTION /by DATE");
        }

        // Check for duplicates before adding the task.
        if (storage.isDuplicateTask(tasks.getTasks(), deadline)) {
            throw new DukeException("This task already exists.");
        }

        tasks.addTask(deadline);
        ui.showTaskAddedMessage(tasks);
    }

    /**
     * Returns a boolean indicating whether the command is an exit command.
     *
     * @return A boolean indicating whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
