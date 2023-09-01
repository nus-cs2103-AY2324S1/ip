package command;

import duke.Storage;
import duke.Ui;
import task.Deadline;
import task.TaskList;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    protected String description;
    protected LocalDateTime by;
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + deadline
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
