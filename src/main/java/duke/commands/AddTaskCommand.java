package duke.commands;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.service.TaskFactory;
import duke.service.UiService;
import duke.tasks.Task;

import java.util.List;

public class AddTaskCommand extends Command {
    private final String taskType;
    private final List<String> argsList;
    private final TaskFactory taskFactory;

    public AddTaskCommand(Duke dukeBot, UiService uiService,
                          String taskType, List<String> argsList, TaskFactory taskFactory) {
        super(dukeBot, uiService);
        this.taskType = taskType;
        this.argsList = argsList;
        this.taskFactory = taskFactory;
    }

    @Override
    public void execute() {
        try {
            Task task = taskFactory.createTask(taskType, argsList);
            dukeBot.addTask(task);
            uiService.printAddTask(task, dukeBot.getNumberOfTasks());
        } catch (TaskParseException | TimeUtilException e) {
            uiService.printGenericMessage(e.getMessage());
        } catch (DukeStorageException e) {
            uiService.printStorageAddFailure();
        }
    }
}
