package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Event;
import java.time.LocalDateTime;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(CommandType.EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.eventMessage(task);
        ui.taskListSizeMessage(tasks.getSize(), true);
    }
}