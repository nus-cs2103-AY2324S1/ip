package carbonbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import carbonbot.exception.CarbonSerializationException;

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

    /**
     * Deserializes the data into a Task.
     * @param data Serialized task
     * @return Task represented by the string
     * @throws CarbonSerializationException If the data could not be deserialized due to error or corruption
     */
    public static Task deserialize(String data) throws CarbonSerializationException {
        String delimiter = " \\| ";
        String[] args = data.split(delimiter);

        // Check that there is at least one argument to decode the task type
        if (args.length == 0) {
            throw new CarbonSerializationException("Invalid data length");
        }

        // Validate task type
        String taskType = args[0];
        if (!TASK_PREFIX_ARGLEN_MAP.containsKey(taskType)) {
            throw new CarbonSerializationException("Invalid task type");
        }

        // Validate argument length
        int expectedArgLen = TASK_PREFIX_ARGLEN_MAP.get(taskType);
        if (args.length != expectedArgLen) {
            throw new CarbonSerializationException("Invalid data length");
        }

        // Decode common properties
        String taskIsMarked = args[1];
        String taskDescription = args[2];

        Task task;
        try {
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                LocalDateTime dueDate = parseDateTimeString(args[3]);
                task = new Deadline(taskDescription, dueDate);
                break;
            case "E":
                LocalDateTime startTime = parseDateTimeString(args[3]);
                LocalDateTime endTime = parseDateTimeString(args[4]);
                task = new Event(taskDescription, startTime, endTime);
                break;
            default:
                throw new CarbonSerializationException("Invalid task type");
            }
        } catch (DateTimeParseException dte) {
            throw new CarbonSerializationException("Datetime parsing error");
        }

        // Set the marked status of the task
        if (!taskIsMarked.equals("1") && !taskIsMarked.equals("0")) {
            throw new CarbonSerializationException("Invalid task status");
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
