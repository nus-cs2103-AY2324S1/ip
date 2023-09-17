package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to add a tag to a specific task in the task list.
 * This command extends the abstract {@code Command} class.
 */
public class AddTagCommand extends Command {

    /** The index of the task to which the tag will be added. */
    private final int taskIndex;

    /** The tag that will be added to the task. */
    private final String tag;

    /**
     * Constructs an {@code AddTagCommand} with the specified task index and tag.
     *
     * @param taskIndex The index of the task to which the tag will be added.
     * @param tag The tag to be added to the task.
     */
    public AddTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false} as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to add a tag to a specific task.
     * If the task index is invalid, a {@code DukeException} is thrown.
     * The method also ensures that the task list is saved after the tag is added.
     *
     * @param tasks The list of tasks.
     * @param ui The UI interface used for displaying task details.
     * @param storage The storage used for saving the task list.
     * @return A string representation of the task after the tag is added.
     * @throws IOException If there's an issue with file IO during storage save.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }

        Task task = tasks.getTask(taskIndex);
        task.setTag(tag);
        storage.save(tasks);
        return ui.showTaggedTask(task);
    }
}
