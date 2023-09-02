package duke.service;

import duke.exception.TaskParseException;
import duke.exception.TimeUtilException;
import duke.tasks.*;
import duke.util.TimeUtil;

import java.time.LocalDateTime;

public class TaskFactory {

    public Task createTask(String taskType, String taskName, String[] args)
            throws TaskParseException, TimeUtilException {
        if (taskName.isEmpty()) {
            throw new TaskParseException("usage: TaskType(todo | deadline | event) " +
                    "TaskName " +
                    "[/StartDate] [/EndDate]");
        }

        TaskType typeEnum;
        try {
            typeEnum = TaskType.valueOf(taskType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskParseException("Invalid task type: " + taskType);
        }

        switch (typeEnum) {
            case TODO:
                if (args.length != 0) {
                    throw new TaskParseException("Error: Todo tasks should not have arguments");
                }
                return new TodoTask(taskName);
            case DEADLINE:
                if (args.length != 1) {
                    throw new TaskParseException("Usage: deadline taskName /by 10 May 2023");
                }
                if (!args[0].startsWith("by ")) {
                    throw new TaskParseException("Error: Deadline tasks must have an end date prefixed with '/by'.\n");
                }
                // remove "by "
                LocalDateTime endDeadlineDate = TimeUtil.parseDateTimeString(args[0].substring(3).trim());
                return new DeadlineTask(taskName, endDeadlineDate);

            case EVENT:
                if (args.length != 2) {
                    throw new TaskParseException("Usage: event taskName /from 10 May 2023 /to 20 May 2023");
                }
                if (!args[0].startsWith("from ") || !args[1].startsWith("to ")) {
                    throw new TaskParseException("Error: Event tasks must have a start date prefixed with '/from' " +
                            "and an end date prefixed with '/to'.\n");
                }
                // remove "from "
                LocalDateTime startEventDate = TimeUtil.parseDateTimeString(args[0].substring(5).trim());
                // remove "to "
                LocalDateTime endEventDate = TimeUtil.parseDateTimeString(args[1].substring(3).trim());
                return new EventTask(taskName, startEventDate, endEventDate);

            default:
                throw new TaskParseException("Unhandled task type: " + taskType);
        }
    }
}
