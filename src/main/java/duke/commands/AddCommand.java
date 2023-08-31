package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
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
        }
        tasks.add(task);
        ui.showLine();
        ui.showAdd(tasks.size(), task);
        ui.showLine();
        storage.writeData(tasks.getAllTasks());
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
