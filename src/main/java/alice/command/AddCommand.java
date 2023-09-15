package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.Deadline;
import alice.task.Event;
import alice.task.Task;
import alice.task.TaskList;
import alice.task.Todo;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to add a task to the list.
 */
public class AddCommand extends Command {
    private final Task task; // The task to be added.

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Constructs an AddCommand with the given argument.
     *
     * @param argument The argument of the command.
     * @throws DukeException If the argument is invalid.
     */
    public AddCommand(Task.TaskType taskType, String argument) throws DukeException {
        switch (taskType) {
        case TODO:
            this.task = Todo.fromArgument(argument);
            break;
        case DEADLINE:
            this.task = Deadline.fromArgument(argument);
            break;
        case EVENT:
            this.task = Event.fromArgument(argument);
            break;
        default:
            throw new DukeException("OOPS!!! I don't know what that means :-(");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        storage.save(tasks.toFileString());
        return ui.showAddTask(this.task, tasks.size());
    }
}
