package buddy.commands;
import java.time.LocalDate;

import buddy.Deadline;
import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for adding a Deadline into the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate deadlineDate;

    /**
     * The constructor for an AddDeadlineCommand.
     *
     * @param description The description of the deadline.
     * @param deadlineDate The date when the deadline task is due.
     */
    public AddDeadlineCommand(String description, LocalDate deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Deadline deadline = new Deadline(description, deadlineDate, false);
        tasks.addTask(deadline);
        ui.printAddSuccessMessage(deadline, tasks);
        storage.writeToFile(tasks.getAllTasks());
    }
}
