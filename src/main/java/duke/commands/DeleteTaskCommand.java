package duke.commands;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.service.UiService;
import duke.tasks.Task;

import java.util.List;
import java.util.Optional;

public class DeleteTaskCommand extends Command {
    private final int taskId;

    public DeleteTaskCommand(Duke dukeBot, UiService uiService, int taskId) {
        super(dukeBot, uiService);
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        try {
            Optional<Task> optionalTask = dukeBot.deleteTask(taskId - 1);
            optionalTask.ifPresentOrElse(
                    task -> uiService.printDeleteTask(task, dukeBot.getNumberOfTasks()),
                    () -> uiService.printInvalidTaskIndexProvided(taskId, dukeBot.getNumberOfTasks())
            );
        } catch (DukeStorageException e) {
            uiService.printStorageDeleteFailure();
        }
    }
}
