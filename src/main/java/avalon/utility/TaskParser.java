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

        assert parts.length <= 6 : "Task string should have no more than 6 parts";

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[3];
        int priority = Integer.parseInt(parts[2]);
        Task task = null;

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, parts[4]);
            break;
        case "E":
            task = new Event(description, parts[4], parts[5]);
            break;
        }

        assert task != null;

        if (isDone) {
            task.markDone();
        }

        task.setPriority(priority);

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
            return "T | " + doneStatus + " | " + task.getPriority() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneStatus + " | " + task.getPriority() + " | "
                    + task.getDescription() + " | "
                    + DateTimeParser.dateTimeToString(deadline.getBy()) + " | " + task.getPriority();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneStatus + " | " + task.getPriority() + " | "
                    + task.getDescription() + " | "
                    + DateTimeParser.dateTimeToString(event.getFrom()) + " | "
                    + DateTimeParser.dateTimeToString(event.getTo()) + " | "
                    + task.getPriority();
        } else {
            return "Wrong formatting";
        }
    }
}
