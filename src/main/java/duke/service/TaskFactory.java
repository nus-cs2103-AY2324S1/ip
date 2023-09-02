package duke.service;

import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.tasks.*;
import duke.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.List;

public class TaskFactory {

    public Task createTask(String taskType, List<String> argsList)
            throws TaskParseException, TimeUtilException {

        if (argsList.isEmpty()) {
            throw new TaskParseException("usage: TaskType(todo | deadline | event) " +
                    "TaskName " +
                    "[/StartDate] [/EndDate]");
        }
        String taskName = argsList.remove(0);

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
                if (!argsList.get(0).startsWith("by ")) {
                    throw new TaskParseException("Error: Deadline tasks must have an end date prefixed with '/by'.\n");
                }
                // remove "by "
                LocalDateTime endDeadlineDate = TimeUtil.parseDateTimeString(
                        argsList.get(0).substring(3).trim()
                );
                return new DeadlineTask(taskName, endDeadlineDate);

            case EVENT:
                if (argsList.size() != 2) {
                    throw new TaskParseException("Usage: event taskName /from 10 May 2023 /to 20 May 2023");
                }
                if (!argsList.get(0).startsWith("from ") || !argsList.get(1).startsWith("to ")) {
                    throw new TaskParseException("Error: Event tasks must have a start date prefixed with '/from' " +
                            "and an end date prefixed with '/to'.\n");
                }
                // remove "from "
                LocalDateTime startEventDate = TimeUtil.parseDateTimeString(
                        argsList.get(0).substring(5).trim()
                );
                // remove "to "
                LocalDateTime endEventDate = TimeUtil.parseDateTimeString(
                        argsList.get(1).substring(3).trim()
                );
                return new EventTask(taskName, startEventDate, endEventDate);

            default:
                throw new TaskParseException("Unhandled task type: " + taskType);
        }
    }
}
