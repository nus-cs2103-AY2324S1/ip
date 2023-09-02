package haste.tasks;

import haste.commands.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by, boolean isComplete) {
        super(description,isComplete);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatTime(this.by) + ")";
    }

    @Override
    public String save() {
        return "D|" + super.save() + "|" + Parser.getCmd(this.by);
    }
}
