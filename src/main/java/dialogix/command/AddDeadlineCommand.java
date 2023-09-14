package dialogix.command;

import java.util.Date;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;
import dialogix.task.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private String deadlineBy;
    private Date deadlineDate;

    /**
     * Constructs an AddDeadlineCommand with a description and a deadline in string format.
     *
     * @param description The description of the deadline task.
     * @param deadlineBy  The deadline in string format.
     */
    public AddDeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    /**
     * Constructs an AddDeadlineCommand with a description and a deadline in Date format.
     *
     * @param description   The description of the deadline task.
     * @param deadlineDate The deadline in Date format.
     */
    public AddDeadlineCommand(String description, Date deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Executes the AddDeadlineCommand by adding a deadline task to the task list and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Deadline deadline = validateDeadline();
        tasks.add(deadline);
        ui.printAddSuccessMessage(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Validates and creates a Deadline object based on the provided information.
     *
     * @return A Deadline object.
     */
    private Deadline validateDeadline() {
        Deadline deadline;
        if (deadlineDate == null) {
            deadline = new Deadline(description, deadlineBy);
        } else {
            deadline = new Deadline(description, deadlineDate);
        }
        return deadline;
    }
}

