package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import java.time.LocalDateTime;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.deadlineMessage(task);
        ui.taskListSizeMessage(tasks.getSize(), true);
    }
}