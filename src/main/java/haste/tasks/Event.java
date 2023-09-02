package haste.tasks;

import haste.commands.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isComplete) {
        super(description,isComplete);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.formatTime(this.start) + " to: " + Parser.formatTime(this.end) + ")";
    }
    @Override
    public String save() {
        return "E|" + super.save() + "|" + Parser.getCmd(this.start) + "|" + Parser.getCmd(this.end);
    }

}
