package grumpygordon.commands;

import java.time.LocalDateTime;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.Event;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

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
     * @param ui The user interface
     * @param storage The storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(this.description, this.from, this.to, false));
        ui.showCommandMessage("     Event task added to list\n     "
                + tasks.getTask(tasks.size() - 1).toString() + "\n");
        storage.saveTasks(tasks);
    }
}
