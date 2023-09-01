package pippi.task;

import pippi.parser.DateFormatter;

import java.time.LocalDate;
public class Deadline extends Task {
    private LocalDate due;
    public Deadline(String desc, LocalDate due) {
        super(desc);
        this.due = due;
    }

    /**
     * Returns the String representation of a deadline task to the UI
     * @return Deadline string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.convertDateToString(due) + ")";
    }

    /**
     * Returns the String representation of a deadline task written to the memory
     * @return Deadline string representation
     */
    @Override
    public String toMemory() { return "D " + super.getStatus() + super.getDescription() + " | " + due.format(DateFormatter.format); }

}
