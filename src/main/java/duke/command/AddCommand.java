package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Map;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Task.TaskType;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String description;
    private TemporalAccessor date1;
    private TemporalAccessor date2;
    private boolean isCompleted;

    private boolean isSilent;

    /**
     * Constructor for AddCommand.
     *
     * @param parameterMap Map of parameters for the command.
     * @param taskType Type of task to add.
     * @throws DukeException If the parameters are invalid.
     */
    public AddCommand(Map<String, String> parameterMap, TaskType taskType) throws DukeException {
        this(parameterMap, taskType, false, false);
    }

    /**
     * Constructor for AddCommand.
     *
     * @param parameterMap Map of parameters for the command.
     * @param taskType Type of task to add.
     * @param isCompleted Whether the task should be marked as completed.
     * @throws DukeException If the parameters are invalid.
     */
    public AddCommand(Map<String, String> parameterMap, TaskType taskType, boolean isCompleted) throws DukeException {
        this(parameterMap, taskType, isCompleted, false);
    }

    /**
     * Constructor for AddCommand.
     *
     * @param parameterMap Map of parameters for the command.
     * @param taskType Type of task to add.
     * @param isCompleted Whether the task should be marked as completed.
     * @param isSilent Whether the command should be silent.
     * @throws DukeException If the parameters are invalid.
     */
    public AddCommand(Map<String, String> parameterMap, TaskType taskType,
            boolean isCompleted, boolean isSilent) throws DukeException {
        super(parameterMap);

        this.taskType = taskType;
        this.isCompleted = isCompleted;
        this.isSilent = isSilent;

        this.loadParameters();
        this.checkIfParametersSpecified();
        this.checkIfParametersValid();
    }

    @Override
    protected void loadParameters() throws DukeException {
        description = parameterMap.get("default");

        switch(taskType) {
        case EVENT:
            date1 = Parser.parseDateTimeInput(parameterMap.get("from"));
            date2 = Parser.parseDateTimeInput(parameterMap.get("to"));
            break;
        case DEADLINE:
            date1 = Parser.parseDateTimeInput(parameterMap.get("by"));
            break;
        case TODO:
        default:
            // No date parameters to load
        }
    }

    @Override
    protected void checkIfParametersSpecified() throws DukeException {
        if (description == null || description.isEmpty()) {
            throw new DukeException("No description specified. Please specify a description.");
        }

        switch(taskType) {
        case EVENT:
            if (date1 == null) {
                throw new DukeException("No start date/time specified. Please specify a start date/time.");
            }

            if (date2 == null) {
                throw new DukeException("No end date/time specified. Please specify an end date/time.");
            }
            break;
        case DEADLINE:
            if (date1 == null) {
                throw new DukeException("No due date/time specified. Please specify a due date/time.");
            }
            break;
        case TODO:
        default:
            // No additional parameters to check
        }
    }

    @Override
    protected void checkIfParametersValid() throws DukeException {
        if (taskType != TaskType.EVENT) {
            return;
        }

        if (date1 instanceof LocalDate) {
            if (!(date2 instanceof LocalDate)) {
                throw new DukeException("Please ensure that both arguments have the same format.");
            }

            LocalDate startDate = (LocalDate) date1;
            LocalDate endDate = (LocalDate) date2;

            if (startDate.isAfter(endDate)) {
                throw new DukeException("Start date cannot be after the end date");
            }
        }

        // If the start date is a LocalDateTime, the end date must also be a LocalDateTime
        if (date1 instanceof LocalDateTime) {
            if (!(date2 instanceof LocalDateTime)) {
                throw new DukeException("Please ensure that both arguments have the same format.");
            }

            LocalDateTime startDate = (LocalDateTime) date1;
            LocalDateTime endDate = (LocalDateTime) date2;

            if (startDate.isAfter(endDate)) {
                throw new DukeException("Start date cannot be after the end date");
            }
        }

        assert this.taskType != null : "Task type should not be null";
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task taskToAdd = null;

        try {
            switch (taskType) {
            case TODO:
                taskToAdd = new Todo(description, isCompleted);
                break;
            case DEADLINE:
                taskToAdd = new Deadline(description, date1, isCompleted);
                break;
            case EVENT:
                taskToAdd = new Event(description, date1, date2, isCompleted);
                break;
            default:
                throw new DukeException("Invalid task type.");
            }
            assert taskToAdd != null : "Task to add should not be null";

            tasks.addTask(taskToAdd);

            // Exit early and do not print anything if the command is silent
            if (isSilent) {
                return null;
            }

            tasks.storeTasks(storage);

            StringBuilder response = new StringBuilder("Got it. I've added this task:\n");
            response.append(String.format("  %s\n", taskToAdd.toString()));
            response.append(String.format("Now you have %d tasks in the list.", tasks.size()));

            return response.toString();
        } catch (DukeException e) {
            throw e;
        }

    }
}
