package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.DukeList;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;



/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime till;
    private String type;

    /**
     * Constructs a command to add a ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    /**
     * Constructs a command to add a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the task.
     */
    public AddCommand(String description, LocalDateTime by) {
        this.description = description;
        this.type = "deadline";
        this.till = by;
    }

    /**
     * Constructs a command to add an Event task.
     *
     * @param description The description of the Event task.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.type = "event";
        this.from = from;
        this.till = to;
    }

    /**
     * Executes the command to add a task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = null;
        switch (this.type) {
        case "todo":
            task = new ToDo(this.description);
            break;
        case "deadline":
            task = new Deadline(this.description, this.till);
            break;
        case "event":
            task = new Event(this.description, this.from, this.till);
            break;
        default:
            throw new DukeException("Invalid task type!");
        }
        tasks.add(task);
        ui.acknowledgeAdd(tasks.getSize(), task);
        storage.updateStorage(tasks.getArrayList());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
