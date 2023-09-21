package grumpygordon.commands;

import java.time.LocalDateTime;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.Event;
import grumpygordon.tasks.TaskList;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {

    /**
     * Description of the event task.
     */
    private final String description;

    /**
     * Start time of the event task.
     */
    private final LocalDateTime from;

    /**
     * End time of the event task.
     */
    private final LocalDateTime to;

    /**
     * Constructor of EventCommand.
     * @param description Description of the event task
     * @param from Start time of the event task
     * @param to End time of the event task
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param storage The storage
     * @return The output string
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(new Event(this.description, this.from, this.to, false));
        storage.saveTasks(tasks);
        return "Event task added to list\n" + tasks.getTask(tasks.size() - 1).toString();
    }
}
