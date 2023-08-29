import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime deadline;
    public Event(String task, String start, String deadline) {
        super(task);
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(start, parseFormatter);
            this.start = parsedDeadline;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect datetime format used for /by, defaulting to current datetime");
            LocalDateTime parsedDeadline = LocalDateTime.now();
            this.start = parsedDeadline;
        }
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, parseFormatter);
            this.deadline = parsedDeadline;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect datetime format used for /to, defaulting to current datetime");
            LocalDateTime parsedDeadline = LocalDateTime.now();
            this.deadline = parsedDeadline;
        }
    }

    @Override
    public String saveString() {
        String completedString = completed ? "X|" : " |";

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
