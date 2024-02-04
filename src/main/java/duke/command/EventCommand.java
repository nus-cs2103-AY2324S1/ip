package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Event;
import java.time.LocalDateTime;
import duke.ui.Ui;

/**
 * Represents a command that adds an event task.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventCommand object using the superclass constructor and
     * initialises the description and event time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event task.
     * @param to          The end time of the event task.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(CommandType.EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the task list and displays the event task message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @return The response to the user input.
     */
    public String execute(TaskList tasks, Ui ui) {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "User interface should not be null";
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        return ui.showEventMessage(task) + ui.showTaskListSizeMessage(tasks.getSize(), true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventCommand) {
            EventCommand eventCommand = (EventCommand) obj;
            return eventCommand.description.equals(this.description)
                    && eventCommand.from.equals(this.from)
                    && eventCommand.to.equals(this.to);
        }
        return false;
    }
}