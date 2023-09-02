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
     */
    @Override
    public void execute() {
        try {
            Optional<Task> optionalTask = dukeBot.deleteTask(taskId - 1);
            optionalTask.ifPresentOrElse(
                    task -> uiService.printDeleteTask(task, dukeBot.getNumberOfTasks()), () ->
                    uiService.printInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageDeleteFailure();
        }
    }
}
