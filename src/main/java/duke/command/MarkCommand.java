package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Initializes a new instance of the MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as completed.
     */
    public MarkCommand(int taskIndex) {

        this.taskIndex = taskIndex;
    }

    /**
     * Indicates whether the command should exit the application.
     *
     * @return false, as the 'mark' command doesn't terminate the application.
     */
    @Override
    public boolean isExit() {

        return false;
    }

    /**
     * Executes the mark command by marking a task as completed.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface handler.
     * @param storage The storage handler.
     * @throws IOException    If there is an issue with file IO.
     * @throws DukeException  If there's an issue executing the mark command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new DukeException("Invalid input number. :( Please provide a valid task number.");
        }

        tasks.mark(taskIndex);
        storage.save(tasks);
        return ui.showMarkedTask(tasks.getTask(taskIndex));
    }
}
