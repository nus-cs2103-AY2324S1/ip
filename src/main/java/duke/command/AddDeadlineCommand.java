package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;

/**
 * The AddDeadlineCommand class is the command for adding a Deadline into the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate deadlineDate;

    /**
     * The constructor for an AddDeadlineCommand.
     *
     * @param description The description of the deadline.
     * @param deadlineDate The due date of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDate deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    /**
     * This method is used to execute the AddDeadlineCommand.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException On input or file error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, deadlineDate);
        tasks.add(deadline);
        ui.printAddSuccessMessage(deadline, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    /**
     * This method is used to check whether this is an Exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
