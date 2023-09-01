package command;

import duke.Storage;
import duke.Ui;
import task.Event;
import task.TaskList;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.from, this.to);
        tasks.addTask(event);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + event
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
