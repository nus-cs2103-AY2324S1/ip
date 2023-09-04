package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Encapsulates a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for a Deadline object with the given description and by.
     *
     * @param description The description of the deadline.
     * @param by The date/time by which the deadline needs to be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }

    /**
     * Constructs a Deadline object with a given description and by as given in command string.
     *
     * @param command The command to create the deadline.
     * @return The deadline object constructed from the given command.
     */
    public static Deadline createDeadlineFromCommand(String command) throws DukeException {
        if (command.length() <= 9) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!command.contains(" /by ")) {
            throw new DukeException("The deadline command must contain a /by.");
        } else if (command.endsWith(" /by ")) {
            throw new DukeException("The deadline command must contain a description after /by.");
        }

        String[] split = command.substring(9).split(" /by ");
        return new Deadline(split[0], split[1]);
    }

    /**
     * Constructs a Deadline object with the given description, by, and done status.
     *
     * @param storageString The string from duke.txt representing a row of data for a deadline.
     * @return The deadline object constructed from the given storage string.
     */
    public static Deadline createDeadlineFromStorage(String storageString) {
        String[] split = storageString.split(" \\| ");
        String isDone = split[1];
        String taskDescription = split[2];
        String by = split[3];
        Deadline deadline = new Deadline(taskDescription, by);
        if (isDone.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        String byString = by.format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }

    /**
     * Returns the string representation of the deadline for storage in duke.txt.
     *
     * @return The string representation of the deadline for storage in duke.txt.
     */
    public String toStorageString() {
        String byString = by.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "D" + super.toStorageString() + " | " + byString;
    }
}
