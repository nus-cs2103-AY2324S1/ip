package sam.services.parser;

import sam.constants.Message;
import sam.exceptions.DukeException;
import sam.tasks.Deadline;
import sam.tasks.Event;
import sam.tasks.Task;
import sam.tasks.ToDo;

import java.time.LocalDateTime;

/**
 * Converts saved data from hard disk into current TaskList.
 */
public class FileParser {

    public static Task parseTaskFromString(String line) throws DukeException {
        String[] parts = line.split("\\s*\\|\\s*"); // Split using the delimiter "|"
        String taskType = parts[0].trim();
        String status = parts[1].trim();

        if (!status.equals("0") && !status.equals("1")) {
            throw new DukeException(Message.CORRUPTED_FILE_ERROR + line);
        }
        boolean isDone = status.equals("1");

        String taskDescription = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;

        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(taskDescription);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (additionalInfo == null) {
                throw new DukeException(Message.CORRUPTED_FILE_ERROR + line);
            }
            LocalDateTime by = LocalDateTime.parse(additionalInfo);
            Deadline deadline = new Deadline(taskDescription, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            if (additionalInfo == null) {
                throw new DukeException(Message.CORRUPTED_FILE_ERROR + line);
            }
            String[] eventParts = additionalInfo.split(" to ");
            if (eventParts.length != 2) {
                throw new DukeException(Message.CORRUPTED_FILE_ERROR + line);
            }
            LocalDateTime from = LocalDateTime.parse(eventParts[0]);
            LocalDateTime to = LocalDateTime.parse(eventParts[1]);
            Event event = new Event(taskDescription, from, to);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            assert false: "Invalid Task Type saved in Hard disk.";
            return null;
        }
    }
}
