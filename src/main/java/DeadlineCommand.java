import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.by = by;
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.deadlineMessage(task);
        ui.taskListSizeMessage(tasks.size(), true);
    }
}