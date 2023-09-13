package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Adds a new Todo task to the task list.
 */
public class TodoCommand implements Command {
    private final String taskDetail;
    private int indexTask;

    /**
     * Constructs a TodoCommand with the specified details.
     *
     * @param detail The details of the todo task.
     */
    public TodoCommand(String detail) {
        this.taskDetail = detail;
        this.indexTask = 0;
    }

    /**
     * Executes the command by creating and adding a Todo task to the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the todo details are empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskDetail.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            Task curr = new Todo(this.taskDetail);
            tasks.add(curr);
            this.indexTask = tasks.size();
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    /**
     * Loads the todo task from the command details and adds it to the task list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     */
    @Override
    public void loadTask(TaskList tasks) {
        assert tasks != null;
        Task curr = new Todo(this.taskDetail);
        tasks.add(curr);
    }

    /**
     * Undoes the task from the command details and remove it to the task list.
     *
     * @param tasks The list of tasks to which the task will be deleted.
     * @return Command the command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new DeleteCommand(Integer.toString(this.indexTask));
    }
}
