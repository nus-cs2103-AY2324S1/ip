package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.InvalidTaskFormatException;
import duke.exception.InvalidTimeFormatException;


/**
 * Represents an event task that has a name, start time, and end time.
 * Extends the {@link Task} class.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructor for an Event Task.
     * @param task The information about the Event task.
     * @throws DukeException When formatting errors occur.
     */
    public Event(String task) throws DukeException {
        super(task.split("/", 3)[0]);
        String[] taskComponents = task.split("/", 3);
        String[] startTimeComponents;
        String[] endTimeComponents;
        try {
            startTimeComponents = taskComponents[1].split(" ", 3);
            endTimeComponents = taskComponents[2].split(" ", 3);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(task);
        }
        if (startTimeComponents.length < 3) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
        try {
            this.startTime = LocalDateTime.of(LocalDate.parse(startTimeComponents[1]),
                    LocalTime.parse(startTimeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
        if (endTimeComponents.length < 2) {
            throw new InvalidTimeFormatException(taskComponents[2]);
        }
        try {
            this.endTime = LocalDateTime.of(LocalDate.parse(endTimeComponents[1]),
                    LocalTime.parse(endTimeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[2]);
        }
    }

    /**
     * Returns a Event object parsed from the save format.
     * @param savedTask The string representing the task in the save format.
     * @return A new Deadline object.
     * @throws DukeException when the formatting is wrong.
     */
    public static Event parseSaveFormat(String savedTask) throws DukeException {
        String[] components = savedTask.split("\\|", 4);
        Event task = new Event(components[2] + "/from " + components[0] + "/to " + components[1]);
        if (components[3].equals("true")) {
            task.setCompleted();
        } else {
            task.setUncompleted();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.toLocalDate().toString() + "," + startTime.toLocalTime().toString() + " to: "
                + endTime.toLocalDate().toString() + "," + endTime.toLocalTime().toString() + ")";
    }

    /**
     * Converts this Event into a save format string.
     * @return A string representing this Deadline.
     */
    public String toSaveFormat() {
        return "event:"
                + startTime.toLocalDate().toString() + " " + startTime.toLocalTime().toString() + "|"
                + endTime.toLocalDate().toString() + " " + endTime.toLocalTime() + "|" + super.toSaveFormat();
    }
}
