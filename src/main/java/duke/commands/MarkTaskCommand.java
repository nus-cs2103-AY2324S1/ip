package duke.commands;

import java.util.Optional;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.service.UiService;
import duke.tasks.Task;

/**
 * Represents a command to mark a task as completed in the Duke application.
 */
public class MarkTaskCommand extends Command {
    private final int taskId;

    /**
     * Constructs a MarkTaskCommand.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     * @param taskId    The ID of the task to be marked.
     */
    public MarkTaskCommand(Duke dukeBot, UiService uiService, int taskId) {
        super(dukeBot, uiService);
        this.taskId = taskId;
    }

    /**
     * Executes the command to mark a task as completed.
     */
    @Override
    public void execute() {
        try {
            Optional<Task> optionalTask = dukeBot.markTask(taskId - 1);
            optionalTask.ifPresentOrElse(
                    uiService::printMarkTask, () ->
                    uiService.printInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageMarkFailure();
        }
    }
}
