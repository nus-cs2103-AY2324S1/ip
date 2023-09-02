package duke.service;

import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.tasks.*;
import duke.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Factory class responsible for creating Task objects based on their type and given arguments.
 * <p>
 * This class provides an abstraction over the task creation process. It receives a task type and
 * a list of arguments, then produces the corresponding task instance.
 * </p>
 */
public class TaskFactory {

    /**
     * Creates a task instance based on the provided task type and arguments.
     *
     * @param taskType The type of task to be created. It should match one of the enum values in {@code TaskType}.
     * @param argsList The list of arguments required for the task. The first item is always the task name.
     *                 Further items depend on the task type (e.g., due date for deadlines, start and end times for events).
     * @return The created Task object.
     * @throws TaskParseException If there's an error in the task's format or arguments.
     * @throws TimeUtilException  If there's an error in parsing the date and time.
     */
    public Task createTask(String taskType, List<String> argsList)
            throws TaskParseException, TimeUtilException {

        if (argsList.isEmpty()) {
            throw new TaskParseException("usage: TaskType(todo | deadline | event) " +
                    "TaskName " +
                    "[/StartDate] [/EndDate]");
        }

        String taskName = argsList.remove(0);

        // Convert the provided taskType string to the TaskType enum.
        TaskType typeEnum;
        try {
            typeEnum = TaskType.valueOf(taskType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskParseException("Invalid task type: " + taskType);
        }

        switch (typeEnum) {
            case TODO:
                if (!argsList.isEmpty()) {
                    throw new TaskParseException("Error: Todo tasks should not have arguments");
                }
                return new TodoTask(taskName);

            case DEADLINE:
                if (argsList.size() != 1) {
                    throw new TaskParseException("Usage: deadline taskName /by 10 May 2023");
                }
                // Ensuring the deadline time is prefixed with "by ".
                if (!argsList.get(0).startsWith("by ")) {
                    throw new TaskParseException("Error: Deadline tasks must have an end date prefixed with '/by'.\n");
                }
                // Parsing the end date without the "by " prefix.
                LocalDateTime endDeadlineDate = TimeUtil.parseDateTimeString(
                        argsList.get(0).substring(3).trim()
                );
                return new DeadlineTask(taskName, endDeadlineDate);

            case EVENT:
                if (argsList.size() != 2) {
                    throw new TaskParseException("Usage: event taskName /from 10 May 2023 /to 20 May 2023");
                }
                // Ensuring the start and end dates are prefixed correctly for event tasks.
                if (!argsList.get(0).startsWith("from ") || !argsList.get(1).startsWith("to ")) {
                    throw new TaskParseException("Error: Event tasks must have a start date prefixed with '/from' " +
                            "and an end date prefixed with '/to'.\n");
                }
                // Parsing the start date without the "from " prefix and the end date without the "to " prefix.
                LocalDateTime startEventDate = TimeUtil.parseDateTimeString(
                        argsList.get(0).substring(5).trim()
                );
                LocalDateTime endEventDate = TimeUtil.parseDateTimeString(
                        argsList.get(1).substring(3).trim()
                );
                return new EventTask(taskName, startEventDate, endEventDate);

            default:
                throw new TaskParseException("Unhandled task type: " + taskType);
        }
    }
}
