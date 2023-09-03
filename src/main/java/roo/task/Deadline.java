package roo.task;

import roo.RooException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String task, String date) throws RooException {
        super(task);
        if (date.isEmpty() || date.equals(" ")) {
            throw new RooException("What is your DEADLINE???\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.date = LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.date = LocalDateTime.parse(date, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    public Deadline(String task, String date, boolean finish) throws RooException{
        super(task, finish);
        if (date.isEmpty() || date.equals(" ")) {
            throw new RooException("What is your DEADLINE???\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.date = LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.date = LocalDateTime.parse(date, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
    }
}
