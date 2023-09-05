package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.InvalidTaskFormatException;
import duke.exception.InvalidTimeFormatException;



/**
 * Represents a deadline task that has a name and deadline time.
 * Extends the {@link Task} class.
 */
public class Deadline extends Task {
    private final LocalDateTime deadlineTime;

    /**
     * Constructor for a Deadline task.
     * @param task The information about the Deadline task.
     * @throws DukeException when formatting errors occur.
     */
    public Deadline(String task) throws DukeException {
        super(task.split("/", 2)[0]);
        String[] taskComponents = task.split("/", 2);
        String[] timeComponents;
        try {
            timeComponents = taskComponents[1].split(" ", 3);
            if (timeComponents.length < 3) {
                throw new InvalidTimeFormatException(taskComponents[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(task);
        }
        try {
            deadlineTime = LocalDateTime.of(LocalDate.parse(timeComponents[1]),
                    LocalTime.parse(timeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
    }

    /**
     * Returns a Deadline object parsed from the save format.
     * @param savedTask The string representing the task in the save format.
     * @return A new Deadline object.
     * @throws DukeException when the formatting is wrong.
     */
    public static Deadline parseSaveFormat(String savedTask) throws DukeException {
        String[] components = savedTask.split("\\|", 3);
        Deadline task = new Deadline(components[1] + "/by " + components[0]);
        if (components[2].equals("true")) {
            task.setCompleted();
        } else {
            task.setUncompleted();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime.toLocalDate().toString() + ","
                + deadlineTime.toLocalTime().toString() + ")";
    }

    /**
     * Converts this Deadline into a save format string.
     * @return A string representing this Deadline.
     */
    public String toSaveFormat() {
        return "deadline:" + deadlineTime.toLocalDate().toString() + " "
                + deadlineTime.toLocalTime().toString() + "|" + super.toSaveFormat();
    }
}
