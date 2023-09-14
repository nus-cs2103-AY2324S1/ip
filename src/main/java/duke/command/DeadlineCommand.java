package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

/**
 * A command class to create deadline tasks.
 */
public class DeadlineCommand extends Command {

    private LocalDate deadline;
    private String description;
    private String location;

    /**
     * Creates a deadline command object.
     *
     * @param description
     * @param deadline
     * @throws DukeException
     */
    public DeadlineCommand(String description, LocalDate deadline) throws DukeException {
        // the inputs should not be empty
        assert description != null : "description should not be empty";
        assert deadline != null : "deadline should not be empty";

        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates a deadline command object.
     * This method has an extra location parameter.
     *
     * @param description
     * @param deadline
     * @throws DukeException
     */
    public DeadlineCommand(String description, String location, LocalDate deadline)
            throws DukeException {
        // the inputs should not be empty
        assert description != null : "description should not be empty";
        assert deadline != null : "deadline should not be empty";
        this.description = description;
        this.location = location;
        this.deadline = deadline;
    }

    /**
     * Executes the command. Adds a todo task to the tasks TaskList.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, this.location, this.deadline);
        tasks.addTask(task);
        ui.display("    Added Deadline to the list of tasks:");
        ui.display("    " + task);
        ui.display("    You currently have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.saveToStorage());
        } catch (IOException e) {
            throw new DukeException(
                    "Something went wrong while trying to save the tasks to the disk!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        DeadlineCommand o = (DeadlineCommand) other;
        return this.deadline.equals(o.deadline) && this.description.equals(o.description);
    }
}
