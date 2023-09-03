package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new Event task to the task list.
 */

public class EventCommand implements Command {
    private final String details;

    /**
     * Constructs an EventCommand with the specified details.
     *
     * @param details The details of the event task.
     */
    public EventCommand(String details) {
        this.details = details;
    }

    /**
     * Executes the command by creating and adding an Event task to the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks to which the new task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the event details are empty.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.\n");
        } else {
            String[] partFrom = details.split("/from");
            String[] partTo = partFrom[1].split("/to");
            Task curr = new Event(partFrom[0], partTo[0].trim(), partTo[1].trim());
            tasks.add(curr);
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    /**
     * Loads the event task from the command details and adds it to the task list.
     *
     * @param tasks The list of tasks to which the new task will be added.
     */
    @Override
    public void loadTask(TaskList tasks) {
        String[] partFrom = details.split("/from");
        String[] partTo = partFrom[1].split("/to");
        Task curr = new Event(partFrom[0], partTo[0].trim(), partTo[1].trim());
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
