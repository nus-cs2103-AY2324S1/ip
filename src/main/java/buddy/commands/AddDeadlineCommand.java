package buddy.commands;
import java.time.LocalDate;

import buddy.Deadline;
import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for adding a Deadline into the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "deadline <description> /by <date>\n"
            + "Example: deadline submit proposal /by 2023-12-31";
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, deadlineDate, false);
        tasks.addTask(deadline);
        String response = ui.printAddSuccessMessage(deadline, tasks);
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}
