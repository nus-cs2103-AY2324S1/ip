package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime deadline;
    public Event(String task, String start, String deadline) throws DukeInvalidDateException {
        super(task);
        try {
            this.start = LocalDateTime.parse(start, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used for /by.");
        }
        try {
            this.deadline = LocalDateTime.parse(deadline, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used for /to.");
        }
    }

    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";

        return "E|" + completedString + task + "|" + start.format(parseFormatter) + "|" + deadline.format(parseFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("d MMM uuuu h:mm a");
        String formattedDeadline = this.deadline.format(toStringFormatter);
        String formattedStart = this.start.format(toStringFormatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedDeadline + ")";
    }
}
