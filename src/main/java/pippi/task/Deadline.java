package pippi.task;

import pippi.parser.DateFormatter;

import java.time.LocalDate;
public class Deadline extends Task {
    private LocalDate due;
    public Deadline(String desc, LocalDate due) {
        super(desc);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.convertDateToString(due) + ")";
    }
    // D | 0 | return book | June 6th
    @Override
    public String toMemory() { return "D " + super.getStatus() + super.getDescription() + " | " + due.format(DateFormatter.format); }

}
