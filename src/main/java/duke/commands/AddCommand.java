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
 * Represents a command to add a task
 */
public class AddCommand extends Command {
    /**
     * The description of the task
     */
    private final String description;
    /**
     * The type of the task, either mark or unmark
     */
    private final String type;
    /**
     * The end time of the task
     */
    private LocalDateTime to;
    /**
     * The start time of the task
     */
    private LocalDateTime from;

    /**
     * duke.tasks.Todo Constructor
     *
     * @param description
     */
    public AddCommand(String description) {
        assert !description.isBlank() : "Task description cannot be empty";
        this.description = description;
        this.type = "todo";
    }

    /**
     * duke.tasks.Deadline Constructor
     *
     * @param description the description of the task
     * @param to          the end time of the task
     */
    public AddCommand(String description, LocalDateTime to) {
        assert !description.isBlank() : "Task description cannot be empty";
        assert !(to == null) : "Task end time cannot be empty";
        this.description = description;
        this.to = to;
        this.type = "deadline";
    }

    /**
     * duke.tasks.Event Constructor
     *
     * @param description the description of the task
     * @param from        the start time of the task
     * @param to          the end time of the task
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        assert !description.isBlank() : "Task description cannot be empty";
        assert !(from == null) : "Task start time cannot be empty";
        assert !(to == null) : "Task end time cannot be empty";
        this.description = description;
        this.to = to;
        this.from = from;
        this.type = "event";
    }

    /**
     * Executes the command
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (this.type) {
        case "todo":
            task = new Todo(this.description);
            break;
        case "deadline":
            task = new Deadline(this.description, this.to);
            break;
        case "event":
            task = new Event(this.description, this.from, this.to);
            break;
        default:
            throw new DukeException("Invalid task type!");
        }
        tasks.add(task);
        storage.writeData(tasks.getAllTasks());
        return ui.showAdd(tasks.getLength(), task);
    }

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
