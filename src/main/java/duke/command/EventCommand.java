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
    private final String taskDetail;
    private int indexTask;

    /**
     * Constructs an EventCommand with the specified details.
     *
     * @param detail The details of the event task.
     */
    public EventCommand(String detail) {
        this.taskDetail = detail;
        this.indexTask = 0;
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
        if (taskDetail.equals("")) {
            throw new DukeException("Error 404!!! The description of a event cannot be empty.\n");
        } else {
            String[] partFrom = taskDetail.split("/from");

            if (partFrom.length != 2) {
                throw new DukeException("Error 404!!! Format should be: <description> /from <date> /to <date>\n");
            }

            String[] partTo = partFrom[1].split("/to");

            if (partTo.length != 2) {
                throw new DukeException("Error 404!!! Format should be: <description> /from <date> /to <date>\n");
            }

            Task curr = new Event(partFrom[0], partTo[0].trim(), partTo[1].trim());
            tasks.add(curr);
            this.indexTask = tasks.size();
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
        assert tasks != null;
        String[] partFrom = taskDetail.split("/from");
        String[] partTo = partFrom[1].split("/to");
        Task curr = new Event(partFrom[0], partTo[0].trim(), partTo[1].trim());
        tasks.add(curr);
    }

    /**
     * Undoes the task from the command details and remove it to the task list.
     *
     * @param tasks The list of tasks to which the task will be deleted.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new DeleteCommand(Integer.toString(this.indexTask));
    }
}
