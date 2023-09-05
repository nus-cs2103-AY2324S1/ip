package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


public class DeadlineTask extends Task {
    private static String type = "D";
    private LocalDate deadline;
    private String deadlineString;

    private void initDeadline(String deadline) {
        if (deadline.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                this.deadline = LocalDate.parse(deadline);
            } catch (DateTimeParseException e) {
                this.deadlineString = deadline;
            }
        } else {
            this.deadlineString = deadline;
        }
    }

    public DeadlineTask(String name, String deadline) {
        super(name);
        initDeadline(deadline);
    }

    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        initDeadline(deadline);
    }

    private String parseLocalDate(LocalDate date) {
        try {
            return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        } catch (DateTimeParseException e) {
            return date.toString();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName(),
                this.deadline != null
                        ? parseLocalDate(this.deadline)
                        : this.deadlineString);
    }
}
