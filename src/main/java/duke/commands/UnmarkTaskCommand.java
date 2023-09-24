package duke.commands;

import java.util.Optional;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.service.UiService;
import duke.tasks.Task;

/**
 * Represents a command to unmark a task as completed in the Duke application.
 */
public class UnmarkTaskCommand extends Command {
    private final int taskId;

    /**
     * Constructs an UnmarkTaskCommand.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     * @param taskId    The ID of the task to be unmarked.
     */
    public UnmarkTaskCommand(Duke dukeBot, UiService uiService, int taskId) {
        super(dukeBot, uiService);
        this.taskId = taskId;
    }

    /**
     * Executes the command to unmark a task as completed.
     *
     * @return A string that represents the task that was unmarked.
     */
    @Override
    public String execute() {
        try {
            Optional<Task> optionalTask = dukeBot.unmarkTask(taskId - 1);
            return optionalTask.map(task -> uiService.formatUnmarkTask(task))
                .orElseGet(() -> uiService.formatInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks()));
        } catch (DukeStorageException e) {
            return uiService.formatStorageUnmarkFailure();
        }
    }
}
