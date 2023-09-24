package duke.commands;

import java.util.Optional;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.service.UiService;
import duke.tasks.Task;

/**
 * Represents a command to delete a task from the Duke application.
 */
public class DeleteTaskCommand extends Command {
    private final int taskId;

    /**
     * Constructs a DeleteTaskCommand.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     * @param taskId    The ID of the task to be deleted.
     */
    public DeleteTaskCommand(Duke dukeBot, UiService uiService, int taskId) {
        super(dukeBot, uiService);
        this.taskId = taskId;
    }

    /**
     * Executes the command to delete a task.
     *
     * @return A string representing the status of the task deletion.
     *         It either confirms the task deletion or details any errors encountered.
     */
    @Override
    public String execute() {
        try {
            Optional<Task> optionalTask = dukeBot.deleteTask(taskId - 1);
            return optionalTask.map(task -> uiService.formatDeleteTask(task, dukeBot.getNumberOfTasks()))
                    .orElseGet(() -> uiService.formatInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks()));
        } catch (DukeStorageException e) {
            return uiService.formatStorageDeleteFailure();
        }
    }
}
