import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime deadline;
    public Deadline(String task, String deadline) {
        super(task);
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, parseFormatter);
            this.deadline = parsedDeadline;
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect datetime format used, defaulting to current datetime");
            LocalDateTime parsedDeadline = LocalDateTime.now();
            this.deadline = parsedDeadline;
        }
    }

    @Override
    public String saveString() {
        String completedString = completed ? "X|" : " |";
        return "D|" + completedString + task + "|" + deadline.format(parseFormatter);
    }

    @Override
    public String toString() {
        String formattedDeadline = this.deadline.format(DateTimeFormatter.ofPattern("d MMM uuuu h:mm a"));
        return "[D]" + super.toString() + " (by: " + formattedDeadline + ")";
    }
}
