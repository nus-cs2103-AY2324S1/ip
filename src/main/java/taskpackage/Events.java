package taskpackage;

import dukepackage.DukeException;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a start and end
 * date. An Events object contains the task,
 * represented by a Task object, as well as the
 * start date and end date both represented by
 * LocalDate objects.
 */
public class Events extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event task with the task, "from" and "to" dates.
     *
     * @param task Task to complete.
     * @param from Start date of event.
     * @param to End date of event.
     * @param isDone Indicator of whether task has been completed.
     * @throws DukeException if there are missing or improper date format used.
     */
    public Events(String task, String from, String to, String isDone) throws DukeException {
        super(task, isDone);

        try {
            this.from = LocalDate.parse(from.substring(5).replaceAll("\\s", ""));
            this.to = LocalDate.parse(to.substring(3));
        } catch (IndexOutOfBoundsException e) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! There are missing details for the event.");
        } catch (DateTimeParseException e) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! Please use the proper format for the deadline (YYYY-MM-DD).");
        }
    }

    /**
     * Prints the "from" and "to" date of the event.
     *
     * @return String
     */
    public String printDetails() {
        return String.format("(from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printTask() {
        return String.format("[E]%s%s", super.printTask(), this.printDetails());
    }

    /**
     * Formats and returns the "from" and "to" date of the event.
     *
     * @return String
     */
    public String addDetailsToStorage() {
        return String.format("| from %s | to %s", this.from, this.to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToStorage() {
        return String.format("E %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
