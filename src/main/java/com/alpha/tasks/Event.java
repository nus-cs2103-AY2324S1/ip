package com.alpha.tasks;

import java.time.LocalDateTime;

import com.alpha.enums.TagEnum;
import com.alpha.utils.DateTimeParser;

/**
 * The Event task..
 */
public class Event extends Task {

    private final LocalDateTime start;

    private final LocalDateTime end;

    /**
     * Instantiates a new Event task.
     *
     * @param name  The name of the task.
     * @param start The start datetime as a LocalDateTime object.
     * @param end   The end datetime as a LocalDateTime object.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.setTag(TagEnum.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toStorageString() {
        return "event "
                + getName()
                + " /from "
                + DateTimeParser.parseDateTimeObjectToStore(start)
                + " /to "
                + DateTimeParser.parseDateTimeObjectToStore(end);
    }

    @Override
    public String toString() {
        return super.toString()
                + "(from: "
                + DateTimeParser.parseDateTimeObjectToDisplay(start)
                + " to: "
                + DateTimeParser.parseDateTimeObjectToDisplay(end)
                + ")";
    }
}
