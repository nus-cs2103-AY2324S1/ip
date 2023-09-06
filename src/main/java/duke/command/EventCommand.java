package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `EventCommand` class represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    private String name;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new `EventCommand` with the specified name, start date and time, and end date and time.
     *
     * @param name The name of the event task.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public EventCommand(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.addEvent(name, from, to);
        storage.writeData(items.getItems());
        return ui.addItem(item.toString(), items.getCount());
    }
}
