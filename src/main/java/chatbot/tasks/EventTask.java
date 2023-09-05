package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class EventTask extends Task {
    private static String type = "E";
    private LocalDate from;
    private LocalDate to;
    private String fromAsString;
    private String toAsString;

    private void initFrom(String from) {
        if (from.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                this.from = LocalDate.parse(from);
            } catch (DateTimeParseException e) {
                this.fromAsString = from;
            }
        } else {
            this.fromAsString = from;
        }
    }

    private void initTo(String to) {
        if (to.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                this.to = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                this.toAsString = to;
            }
        } else {
            this.toAsString = to;
        }
    }

    public EventTask(String name, String from, String to) {
        super(name);
        initFrom(from);
        initTo(to);
    }

    public EventTask(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        initFrom(from);
        initTo(to);
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
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName(),
                this.from != null
                        ? parseLocalDate(this.from)
                        : this.fromAsString,
                this.to != null
                        ? parseLocalDate(this.to)
                        : this.toAsString);
    }
}
