package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * The `DeadlineCommand` class represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    private String name;
    private LocalDateTime by;

    /**
     * Constructs a new `DeadlineCommand` with the specified name and deadline.
     *
     * @param name The name of the deadline task.
     * @param by   The deadline date and time.
     */
    public DeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.addDeadline(name, by);
        ui.addItem(item.toString(), items.getCount());
        storage.writeData(items.getItems());
    }
}
