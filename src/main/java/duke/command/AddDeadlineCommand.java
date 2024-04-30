package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDateTime;

/**
 * Represents the command to add deadlines.
 */
public class AddDeadlineCommand extends Command {
    /** Description of the deadline task. */
    private String description;
    /** String that represents the deadline due date. */
    private String deadlineString;
    /** Deadline date of the task. */
    private LocalDateTime deadlineDate;

    /**
     * Constructor used when the date is still a String.
     * 
     * @param description Description of the Deadline task.
     * @param deadline String representation of the deadline date.
     */
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadlineString = deadline;
    }

    /**
     * Constructor used when the date is represented in LocalDateTime.
     * 
     * @param description Description of the Deadline task.
     * @param deadlineDate Date for the deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (deadlineDate == null) {
            deadline = new Deadline(description, deadlineString);
        } else {
            deadline = new Deadline(description, deadlineDate);
            assert (deadline != null) : "deadline invalid!";
        }
        taskList.add(deadline);
        ui.addToListSuccess(deadline, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
