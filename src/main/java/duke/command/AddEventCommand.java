package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String start;
    private String end;

    public AddEventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event;
        if (startTime == null && endTime == null) {
            event = new Event(description, start, end);
        } else {
            event = new Event(description, startTime, endTime);
        }
        taskList.add(event);
        ui.addToListSuccess(event, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
