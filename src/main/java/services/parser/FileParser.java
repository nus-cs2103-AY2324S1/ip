package services.parser;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDateTime;

public class FileParser {

    public static Task parseTaskFromString(String line) throws DukeException {
        String[] parts = line.split("\\s*\\|\\s*"); // Split using the delimiter "|"
        String taskType = parts[0].trim();
        String status = parts[1].trim();

        if (!status.equals("0") && !status.equals("1")) {
            throw new DukeException("☹ OOPS!!! Saved data not found due to corruption. \n Corrupted task status: " + line);
        }
        boolean isDone = status.equals("1");

        String taskDescription = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;

        switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDescription);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (additionalInfo == null) {
                    throw new DukeException("☹ OOPS!!! Saved data not found due to corruption. \n Missing date. Corrupted deadline: " + line);
                }
                LocalDateTime by = LocalDateTime.parse(additionalInfo);
                Deadline deadline = new Deadline(taskDescription, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (additionalInfo == null) {
                    throw new DukeException("☹ OOPS!!!  Saved data not found due to corruption. \n Missing details. Corrupted event: " + line);
                }
                String[] eventParts = additionalInfo.split(" to ");
                if (eventParts.length != 2) {
                    throw new DukeException("☹ OOPS!!!  Saved data not found due to corruption. \n Missing details. Corrupted event: " + line);
                }
                LocalDateTime from = LocalDateTime.parse(eventParts[0]);
                LocalDateTime to = LocalDateTime.parse(eventParts[1]);
                Event event = new Event(taskDescription, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }
}
