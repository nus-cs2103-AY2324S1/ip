package carbonbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import carbonbot.exception.CarbonDataFileException;

/**
 * Helper class to handle deserialization of task data.
 */
public class TaskDeserializer {

    /**
     * Mapping of the task prefix to the number of expected arguments.
     * For example, deadline tasks should have four arguments,
     * [D] | [isMarked] | [description] | [dueDate]
     */
    private static final Map<String, Integer> TASK_PREFIX_ARGLEN_MAP = Map.of(
            "T", 3,
            "D", 4,
            "E", 5
    );

    private static final String DELIMITER = " \\| ";
    private static final int ARG_TASK_TYPE_IDX = 0;
    private static final int ARG_TASK_MARKED_IDX = 1;
    private static final int ARG_TASK_DESCRIPTION_IDX = 2;
    private static final int ARG_TASK_DUE_DATE_IDX = 3;
    private static final int ARG_TASK_START_DATE_IDX = 3;
    private static final int ARG_TASK_END_DATE_IDX = 4;

    private static void validateArguments(String[] args) throws CarbonDataFileException {
        // Check that there is at least one argument to decode the task type
        if (args.length == 0) {
            throw new CarbonDataFileException("Invalid data length");
        }

        // Validate task type
        String taskType = args[0];
        if (!TASK_PREFIX_ARGLEN_MAP.containsKey(taskType)) {
            throw new CarbonDataFileException("Invalid task type");
        }

        // Validate argument length
        int expectedArgLen = TASK_PREFIX_ARGLEN_MAP.get(taskType);
        if (args.length != expectedArgLen) {
            throw new CarbonDataFileException("Invalid data length");
        }
    }

    /**
     * Deserializes the data into a Task.
     * @param data Serialized task.
     * @return Task represented by the string.
     * @throws CarbonDataFileException If the data could not be deserialized due to error or corruption.
     */
    public static Task deserialize(String data) throws CarbonDataFileException {
        String[] args = data.split(DELIMITER);
        validateArguments(args);

        String taskType = args[ARG_TASK_TYPE_IDX];
        String taskIsMarked = args[ARG_TASK_MARKED_IDX];
        String taskDescription = args[ARG_TASK_DESCRIPTION_IDX];
        Task task;
        try {
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                LocalDateTime dueDate = parseDateTimeString(args[ARG_TASK_DUE_DATE_IDX]);
                task = new Deadline(taskDescription, dueDate);
                break;
            case "E":
                LocalDateTime startTime = parseDateTimeString(args[ARG_TASK_START_DATE_IDX]);
                LocalDateTime endTime = parseDateTimeString(args[ARG_TASK_END_DATE_IDX]);
                task = new Event(taskDescription, startTime, endTime);
                break;
            default:
                throw new CarbonDataFileException("Invalid task type");
            }
        } catch (DateTimeParseException dte) {
            throw new CarbonDataFileException("Datetime parsing error");
        }

        // Set the marked status of the task
        if (!taskIsMarked.equals("1") && !taskIsMarked.equals("0")) {
            throw new CarbonDataFileException("Invalid task status");
        }
        if (taskIsMarked.equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private static LocalDateTime parseDateTimeString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
