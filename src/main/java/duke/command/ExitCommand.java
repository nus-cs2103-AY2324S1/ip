package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.task.Task;

import java.io.IOException;

/**
 * The ExitCommand represents a command for exiting the chatbot application.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an exit command to exit the chatbot application.
     *
     * @param task The task associated with the command.
     */
    public ExitCommand(Task task) {
        super(task);
    }

    /**
     * Executes the command and displays parting messages through user interface.
     *
     * @param tasks The task list to interact with (not used).
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks (not used).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showParting();
    }
}
