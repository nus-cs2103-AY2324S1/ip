package avalon.utility;

import avalon.task.Deadline;
import avalon.task.Event;
import avalon.task.Task;
import avalon.task.ToDo;

/**
 * Utility class for parsing and serializing tasks.
 */
public class TaskParser {

    /**
     * Parses a string representation of a task and returns the corresponding Task object.
     *
     * @param line The string representation of the task to be parsed.
     * @return The Task object parsed from the input string.
     */
    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    /**
     * Serializes a Task object to a string for saving to a file.
     *
     * @param task The Task object to be serialized.
     * @return The string representation of the serialized task.
     */
    public static String serialize(Task task) {
        String doneStatus = task.getIsDone() ? "1" : "0";

        if (task instanceof ToDo) {
            return "T | " + doneStatus + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneStatus + " | " + task.getDescription() + " | "
                    + DateTimeParser.dateTimeToString(deadline.getBy());
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneStatus + " | " + task.getDescription() + " | "
                    + DateTimeParser.dateTimeToString(event.getFrom()) + " | "
                    + DateTimeParser.dateTimeToString(event.getTo());
        } else {
            return "Wrong formatting";
        }
    }
}
