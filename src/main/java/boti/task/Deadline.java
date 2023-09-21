package boti.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import boti.exception.InvalidDeadlineException;

/**
 * Deadline class
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Instantiates deadline
     *
     * @param name the name of the deadline
     * @param deadline the date of the deadline
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline based on the message
     *
     * @param message the message to create the new deadline
     * @return the new deadline
     * @throws InvalidDeadlineException when the deadline command message is invalid
     */
    public static Deadline create(String message) throws InvalidDeadlineException {
        assert message.split(" ")[0].equalsIgnoreCase("deadline") : "First word of message must be deadline";
        try {
            String name = message.substring(9, message.indexOf("/by "));
            String deadlineString = message.substring(message.indexOf("/by ") + 4);
            LocalDate deadlineDate = LocalDate.parse(deadlineString);
            return new Deadline(name, deadlineDate);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeadlineException();
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Creates new deadline based on the string stored in the storage
     *
     * @param stored the string stored in the storage
     * @return the deadline based on the string stored in the storage
     * @throws IOException when the string stored does not belong to deadline
     */
    public static Deadline createFromStorage(String stored) throws IOException {
        assert stored.split(" \\| ")[0].equals("E") : "The first part of the string stored is E";
        String[] splitTaskInString = stored.split(" \\| ");
        String mark = splitTaskInString[1];
        String description = splitTaskInString[2];
        String deadlineDate = splitTaskInString[3];
        Deadline deadline = new Deadline(description, LocalDate.parse(deadlineDate));
        if (mark.equals("1")) {
            deadline.mark();
        }
        return deadline;
    }
    @Override
    public String toString() {
        String deadlineMonth = deadline.getMonth().toString().substring(0, 3);
        String deadlineDay = deadline.toString().split("-")[2];
        String deadlineYear = deadline.toString().split("-")[0];
        return "[D]" + super.toString() + " (by: " + deadlineMonth + " "
                + deadlineDay + " " + deadlineYear + " )";
    }

    @Override
    public String storeInString() {
        return "D | " + (this.getMark() ? "1 | " : "0 | ") + this.getName() + " | " + this.deadline;
    }
}
