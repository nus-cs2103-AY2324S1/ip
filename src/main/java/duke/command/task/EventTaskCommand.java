package duke.command.task;

import java.time.LocalDateTime;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `EventCommand` class represents a command to add an event task to the task list.
 */
public class EventTaskCommand extends Command {

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
    public EventTaskCommand(String name, LocalDateTime from, LocalDateTime to) {
        assert(name != null && from != null && to != null);
        this.name = name;
        this.from = from;
        this.to = to;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        Task item = items.addEvent(name, from, to);
        storage.writeData(items);
        return ui.addItem(item.toString(), items.getCount());
    }
}
