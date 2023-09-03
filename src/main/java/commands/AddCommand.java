package commands;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.ToDo;
import tasks.*;
import ui.Ui;
import java.time.LocalDateTime;
/**
 * The {@code AddCommand} class represents a command to add tasks to a task list.
 * It can handle different types of tasks, including ToDo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {

    // Fields
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private String type;

    /**
     * Constructs an {@code AddCommand} object for adding a ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    /**
     * Constructs an {@code AddCommand} object for adding a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the task (date and time).
     */
    public AddCommand(String description, LocalDateTime by) {
        this.description = description;
        this.type = "deadline";
        this.to = by;
    }

    /**
     * Constructs an {@code AddCommand} object for adding an Event task.
     *
     * @param description The description of the Event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.type = "event";
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the AddCommand by creating the specified task and adding it to the task list.
     *
     * @param tasks   The task list to which the task should be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data.
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        switch (this.type) {
            case "todo":
                task = new ToDo(this.description);
                tasks.add(task);
                ui.todoMessage(task);
                break;
            case "deadline":
                task = new Deadline(this.description, this.to);
                tasks.add(task);
                ui.DeadlineMessage(task);
                break;
            case "event":
                task = new Event(this.description, this.from, this.to);
                tasks.add(task);
                ui.eventMessage(task);
                break;
        }
    }
}
