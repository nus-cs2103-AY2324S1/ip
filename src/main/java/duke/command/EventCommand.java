package duke.command;

import com.sun.jdi.request.EventRequestManager;
import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;


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