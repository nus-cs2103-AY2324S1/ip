package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime deadline;
    public Deadline(String task, String deadline) throws DukeInvalidDateException {
        super(task);
        try {
            this.deadline = LocalDateTime.parse(deadline, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateException("Incorrect datetime format used.");
        }
    }

    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";
        return "D|" + completedString + task + "|" + deadline.format(parseFormatter);
    }

    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("d MMM uuuu h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
