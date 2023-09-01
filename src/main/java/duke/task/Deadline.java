package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDeadlineException;

/**
 * Deadline class
 */
public class Deadline extends Task {
    private LocalDate deadline;

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
