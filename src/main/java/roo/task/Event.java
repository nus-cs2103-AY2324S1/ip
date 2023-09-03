package roo.task;

import roo.RooException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String task, String start, String end) throws RooException {
        super(task);
        if (start.isEmpty() || start.equals(" ")) {
            throw new RooException("Missing time!!!\n");
        } else if (end.isEmpty() || end.equals(" ")) {
            throw new RooException("Missing time!!!\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.start = LocalDateTime.parse(start, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.start = LocalDateTime.parse(start, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your start time: dd-MM-yyyy HH:mm\n");
            }
        }

        try {
            this.end = LocalDateTime.parse(end, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.end = LocalDateTime.parse(end, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your end time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    public Event(String task, String start, String end, boolean finish) throws RooException{
        super(task, finish);
        if (start.isEmpty() || start.equals(" ")) {
            throw new RooException("Missing time !!!\n");
        } else if (end.isEmpty() || end.equals(" ")) {
            throw new RooException("Missing time!!!\n");
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        try {
            this.start = LocalDateTime.parse(start, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.start = LocalDateTime.parse(start, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your start time: dd-MM-yyyy HH:mm\n");
            }
        }

        try {
            this.end = LocalDateTime.parse(end, formatter1);
        } catch (DateTimeParseException err1) {
            try {
                this.end = LocalDateTime.parse(end, formatter2);
            } catch (DateTimeParseException err2) {
                System.err.println("Please use this format for your end time: dd-MM-yyyy HH:mm\n");
            }
        }
    }

    @Override
    public LocalDateTime getDate() {
        return this.start;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " from: " + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
    }
}
