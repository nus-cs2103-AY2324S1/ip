package blip.commands;

import java.time.LocalDateTime;

import blip.priority.Priority;
import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents the event command to add an event task.
 */
public class EventCommand extends Command {
    /**
     * Description of the event task.
     */
    String description;

    Priority priority;

    /**
     * Start time of the event task.
     */
    LocalDateTime eventStart;

    /**
     * End time of the event task.
     */
    LocalDateTime eventEnd;

    /**
     * Creates an instance of EventCommand.
     *
     * @param description The description of the event task
     * @param eventStart The start time of the event task
     * @param eventEnd The end time of the event task
     */
    public EventCommand(String description, LocalDateTime eventStart, LocalDateTime eventEnd, Priority priority) {
        this.description = description;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.priority = priority;
    }

    /**
     * Executes the event command to add an event task.
     *
     * @param taskList The Array List of tasks to add event task into
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        Event eventTask = new Event(description, eventStart, eventEnd, false, this.priority);
        taskList.addTask(eventTask);
        storage.saveToFile(taskList);
        return ui.addsTasksMsg(eventTask, taskList.size());
    }
}
