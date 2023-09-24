package duke.commands;

import java.util.List;

import duke.Duke;
import duke.exception.DukeStorageException;
import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.service.TaskFactory;
import duke.service.UiService;
import duke.tasks.Task;

/**
 * Represents a command to add a task in the Duke application.
 */
public class AddTaskCommand extends Command {
    private final String taskType;
    private final List<String> argsList;
    private final TaskFactory taskFactory;

    /**
     * Constructs an AddTaskCommand.
     *
     * @param dukeBot    The main Duke instance.
     * @param uiService  The UI service for interactions.
     * @param taskType   The type of the task to be added.
     * @param argsList   The arguments required to create the task.
     * @param taskFactory The factory to create tasks.
     */
    public AddTaskCommand(Duke dukeBot, UiService uiService,
                          String taskType, List<String> argsList, TaskFactory taskFactory) {
        super(dukeBot, uiService);
        this.taskType = taskType;
        this.argsList = argsList;
        this.taskFactory = taskFactory;
    }

    /**
     * Executes the command to add a task.
     *
     * @return A string representing the status of the task addition.
     *         It either confirms the task addition or details any errors encountered.
     */
    @Override
    public String execute() {
        try {
            Task task = taskFactory.createTask(taskType, argsList);
            dukeBot.addTask(task);
            return uiService.formatAddTask(task, dukeBot.getNumberOfTasks());
        } catch (TaskParseException | TimeUtilException e) {
            return uiService.formatGenericMessage(e.getMessage());
        } catch (DukeStorageException e) {
            return uiService.formatStorageAddFailure();
        }
    }
}
