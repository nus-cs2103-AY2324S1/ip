package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, String date, String time) {
        super(description);
        this.date = Parser.parseDate(date);
        this.time = Parser.parseTime(time);
    }

    public String reformatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String reformatTime() {
        return this.time.format(DateTimeFormatter.ofPattern("h.mma", Locale.US));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + reformatDate() + " " + reformatTime() + ")";
    }

    @Override
    public String fileDescription() {
        return "D" + super.fileDescription() + " | " + reformatDate() + " | " + reformatTime() + "\n";
    }
}
