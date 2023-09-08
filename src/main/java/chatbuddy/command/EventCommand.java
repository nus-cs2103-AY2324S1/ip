package chatbuddy.command;

import java.time.LocalDateTime;

import chatbuddy.ChatBuddyException;
import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.task.Event;
import chatbuddy.ui.Ui;

/** EventCommand represents a command to create an event task. */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String taskDescription;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an instance of a EventCommand with the given description, 'from' and 'to' datetimes.
     *
     * @param taskDescription The description of the task.
     * @param from The datetime that the event starts from.
     * @param to The datetime that the events ends at.
     */
    public EventCommand(String taskDescription, LocalDateTime from, LocalDateTime to) {
        this.taskDescription = taskDescription;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Event event = new Event(taskDescription, from, to);
        tasks.addTask(event);
        return ui.showTaskAddition(event, tasks.getSize());
    }
}
