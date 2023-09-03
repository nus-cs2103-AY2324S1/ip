package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Represents the AddCommand Class.
 * Responsible for addition operations.
 *
 * @author Shishir
 */
public class AddCommand extends Command {
    /** Description of the task. */
    private String description;

    /** Due date of the task. */
    private LocalDateTime till;

    /** Start date of the task. */
    private LocalDateTime from;

    /** Type of the task. */
    private String type;

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     */
    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     */
    public AddCommand(String description, LocalDateTime till) {
        this.description = description;
        this.till = till;
        this.type = "deadline";
    }

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     * @param from Start date of the task.
     * @param till End date of the task.
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime till) {
        this.description = description;
        this.till = till;
        this.from = from;
        this.type = "event";
    }

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Thrown if task type is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (this.type) {
        case "todo":
            task = new Todo(this.description, false);
            break;
        case "deadline":
            task = new Deadline(this.description, this.till, false);
            break;
        case "event":
            task = new Event(this.description, this.from, this.till, false);
            break;
        default:
            throw new DukeException("Invalid task type!");
        }
        tasks.add(task);
        storage.writeData(tasks.getAllTasks());
        return ui.showAdd(tasks.size(), task);
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
