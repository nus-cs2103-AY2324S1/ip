package blip.commands;

import java.time.LocalDateTime;
import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

public class EventCommand extends Command {
    String description;
    LocalDateTime eventStart;
    LocalDateTime eventEnd;

    public EventCommand(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        this.description = description;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        Event eventTask = new Event(description, eventStart, eventEnd, false);
        taskList.addTask(eventTask);
        storage.saveToFile(taskList);
        ui.addsTasksMsg(eventTask, taskList.size());
    }
}
