package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new Deadline task to the task list.
 */
public class DeadlineCommand implements Command {
    private final String taskDetail;
    private int indexTask;

    /**
     * Constructs a DeadlineCommand with the specified details and save the command.
     *
     * @param detail The details of the deadline task.
     */
    public DeadlineCommand(String detail) {
        this.taskDetail = detail;
        this.indexTask = 0;
    }

    /**
     * Executes the command by creating and adding a Deadline task to the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the deadline details are empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskDetail.equals("")) {
            throw new DukeException("Error 404!!! The description of a deadline cannot be empty.\n");
        } else {
            String[] partDeadline = taskDetail.split("/by");

            if (partDeadline.length != 2) {
                throw new DukeException("Error 404!!! Format should be: <description> /by <deadline>\n");
            }

            Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
            tasks.add(curr);
            this.indexTask = tasks.size();
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    /**
     * Loads the task from the command details and adds it to the task list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     */
    @Override
    public void loadTask(TaskList tasks) {
        assert tasks != null;
        String[] partDeadline = taskDetail.split("/by");
        Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
        tasks.add(curr);
    }

    /**
     * Undoes the task from the command details and remove it to the task list.
     *
     * @param tasks The list of tasks to which the task will be deleted.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new DeleteCommand(Integer.toString(this.indexTask));
    }
}
