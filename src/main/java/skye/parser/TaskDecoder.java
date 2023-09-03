package skye.parser;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.task.Deadline;
import skye.data.task.Event;
import skye.data.task.Task;
import skye.data.task.ToDo;

import java.time.LocalDateTime;

/**
 * Represents a utility class that decodes a task from an encoded string
 */
public class TaskDecoder {

    /**
     * Decodes the encoded string representation of a task.
     *
     * @param line The encoded string representing a task in its save format
     * @return Task
     * @throws DukeException When an invalid save format is encountered
     */
    public Task decode(String line) throws DukeException {
        String[] data = line.split("\\|");
        String taskType = data[0].trim();
        switch (taskType) {
            case "D":
                if (data.length == 4) {
                    LocalDateTime localDateTime =
                            LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT);
                    Task task = new Deadline(data[2].trim(), localDateTime);
                    if (Integer.parseInt(data[1].trim()) == 1) {
                        task.markAsDone();
                    }
                    return task;
                } else {
                    throw new DukeException(DukeExceptionType.INVALID_DEADLINE_SAVE_FORMAT);
                }
            case "E":
                if (data.length == 5) {
                    Task task = new Event(data[2].trim(),
                            LocalDateTime.parse(data[3].trim(), Parser.DATE_TIME_FORMAT),
                            LocalDateTime.parse(data[4].trim(), Parser.DATE_TIME_FORMAT));
                    if (Integer.parseInt(data[1].trim()) == 1) {
                        task.markAsDone();
                    }
                    return task;
                } else {
                    throw new DukeException(DukeExceptionType.INVALID_EVENT_SAVE_FORMAT);
                }
            case "T":
                if (data.length == 3) {
                    Task task = new ToDo(data[2].trim());
                    if (Integer.parseInt(data[1].trim()) == 1) {
                        task.markAsDone();
                    }
                    return task;
                } else {
                    throw new DukeException(DukeExceptionType.INVALID_TODO_SAVE_FORMAT);
                }
            default:
                throw new DukeException(DukeExceptionType.UNKNOWN_TASK_TYPE);
        }
    }
}
