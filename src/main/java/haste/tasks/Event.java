package haste.tasks;

import haste.commands.Parser;

import java.time.LocalDateTime;

/**
 * Represents an Event.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     *
     * @param description Task description.
     * @param start Start time of the Task.
     * @param end End time of the Task.
     * @param isComplete Completion status of Task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isComplete) {
        super(description,isComplete);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.formatTime(this.start) + " to: " + Parser.formatTime(this.end) + ")";
    }
    @Override
    public String save() {
        return "E|" + super.save() + "|" + Parser.getCmd(this.start) + "|" + Parser.getCmd(this.end);
    }

}
