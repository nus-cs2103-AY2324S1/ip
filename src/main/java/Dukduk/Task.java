package dukduk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new task with the given description and sets its completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets an icon representing the completion status of the task.
     *
     * @return "X" if the task is completed, " " (space) if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task by setting its completion status to false.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task in a data-friendly format.
     *
     * @return A formatted string suitable for data storage.
     */
    public abstract String toDataString();

    /**
     * Creates a task object from a data string.
     *
     * @param dataString The data string representing the task.
     * @return A Task object created from the data string.
     * @throws DukdukException If there is an error in parsing the data string or creating the task.
     */
    public static Task createTaskFromDataString(String dataString) throws DukdukException {
        String[] parts = dataString.split(" \\| ");

        if (parts.length < 3) {
            throw new DukdukException("Invalid task data format: " + dataString);
        }

        String type = parts[0];
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description);
            case "D":
                if (parts.length >= 4) {
                    String byString = parts[3];
                    LocalDateTime by = LocalDateTime.parse(byString, 
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    return new Deadline(description, by);
                } else {
                    throw new DukdukException("Invalid Deadline task data format: " + dataString);
                }
            case "E":
                if (parts.length >= 4) {
                    String eventTiming = parts[3];
                    String[] eventParts = eventTiming.split("\\|");
                    if (eventParts.length >= 2) {
                        String from = eventParts[0];
                        String to = eventParts[1];
                        LocalDateTime fromDateTime = LocalDateTime.parse(from, 
                                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                        LocalDateTime toDateTime = LocalDateTime.parse(to, 
                                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                        return new Event(description, fromDateTime, toDateTime);
                    } else {
                        throw new DukdukException("Invalid Event task data format: " + dataString);
                    }
                } else {
                    throw new DukdukException("Invalid Event task data format: " + dataString);
                }
            default:
                throw new DukdukException("Invalid task type in data string: " + type);
        }
    }
}
