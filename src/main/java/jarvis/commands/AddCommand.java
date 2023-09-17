package jarvis.commands;

import static jarvis.exceptions.ExceptionMessages.INVALID_TASK;

import java.time.LocalDateTime;

import jarvis.exceptions.DukeException;
import jarvis.storage.Storage;
import jarvis.tasks.Deadline;
import jarvis.tasks.Event;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.tasks.TaskType;
import jarvis.tasks.Todo;
import jarvis.ui.Ui;

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
    private TaskType type;

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     */
    public AddCommand(String description) {
        assert !description.isBlank() : "Task description is blank!";
        this.description = description;
        this.type = TaskType.TODO;
    }

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     * @param till Deadline of the task.
     */
    public AddCommand(String description, LocalDateTime till) {
        assert !description.isBlank() : "Task description is blank!";
        this.description = description;
        this.till = till;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Constructs the AddCommand Object.
     * @param description Description of the task.
     * @param from Start date of the task.
     * @param till End date of the task.
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime till) {
        assert !description.isBlank() : "Task description is blank!";
        this.description = description;
        this.till = till;
        this.from = from;
        this.type = TaskType.EVENT;
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
        case TODO:
            task = new Todo(this.description, false);
            break;
        case DEADLINE:
            task = new Deadline(this.description, this.till, false);
            break;
        case EVENT:
            task = new Event(this.description, this.from, this.till, false);
            break;
        default:
            throw new DukeException(INVALID_TASK);
        }
        tasks.add(task);
        storage.writeData(tasks.getAllTasks());
        return ui.showAdd(tasks.size(), task);
    }

}
