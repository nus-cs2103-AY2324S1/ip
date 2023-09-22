package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * An event command
 */
public class EventCommand extends Command {

    private String description;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructor for an EventCommand object
     * @param description the description of the event
     * @param fromDateTime the time that the event should start
     * @param toDateTime the time that the event should end
     */
    public EventCommand(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        Event eventTask = new Event(this.description, this.fromDateTime, this.toDateTime);
        taskList.addTask(eventTask);
        storage.saveCurrentTasks(taskList.getTaskArray());
        ui.showMessage("Got it. I've added this task:\n" + eventTask.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }
}
