package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import java.time.LocalDateTime;
import duke.ui.Ui;

/**
 * Represents a command that adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand object using the superclass constructor and
     * initialises the description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the deadline task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task to the task list and displays the deadline task message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showDeadlineMessage(task);
        ui.showTaskListSizeMessage(tasks.getSize(), true);
    }
}