package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }

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

    @Override
    public String toString() {
        String byString = by.format(DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }

    public String toStorageString() {
        String byString = by.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "D" + super.toStorageString() + " | " + byString;
    }
}

