package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String deadlineBy;

    /**
     * Constructs a DeadlineCommand with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadlineBy  The deadline of the task.
     */
    public DeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    /**
     * Executes the DeadlineCommand by adding a new Deadline task to the task list,
     * displaying a completion message, and saving the tasks to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If there's an error during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, deadlineBy);
        tasks.add(deadline);
        ui.displayCompletionMessage(deadline, tasks.size());
        storage.saveTasksToFile(tasks);
    }

}
