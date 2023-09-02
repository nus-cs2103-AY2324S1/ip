package duke.commands;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.service.UiService;
import duke.tasks.Task;

import java.util.List;
import java.util.Optional;

public class UnmarkTaskCommand extends Command {
    private final int taskId;

    public UnmarkTaskCommand(Duke dukeBot, UiService uiService, int taskId) {
        super(dukeBot, uiService);
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        try {
            Optional<Task> optionalTask = dukeBot.unmarkTask(taskId - 1);
            optionalTask.ifPresentOrElse(
                    uiService::printUnmarkTask,
                    () -> uiService.printInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageUnmarkFailure();
        }
    }
}
