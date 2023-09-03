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
    private final String details;

    /**
     * Constructs a DeadlineCommand with the specified details.
     *
     * @param details The details of the deadline task.
     */
    public DeadlineCommand(String details) {
        this.details = details;
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
        if (details.equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
        } else {
            String[] partDeadline = details.split("/by");
            Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
            tasks.add(curr);
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
        String[] partDeadline = details.split("/by");
        Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
        tasks.add(curr);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return `false` indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
