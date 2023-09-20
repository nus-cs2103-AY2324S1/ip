package duke.command.task;

import java.time.LocalDateTime;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `DeadlineCommand` class represents a command to add a deadline task to the task list.
 */
public class DeadlineTaskCommand extends Command {

    private String name;
    private LocalDateTime by;

    /**
     * Constructs a new `DeadlineCommand` with the specified name and deadline.
     *
     * @param name The name of the deadline task.
     * @param by   The deadline date and time.
     */
    public DeadlineTaskCommand(String name, LocalDateTime by) {
        assert(name != null && by != null);
        this.name = name;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.addDeadline(name, by);
        storage.writeData(items);
        return ui.addItem(item.toString(), items.getCount());
    }
}
