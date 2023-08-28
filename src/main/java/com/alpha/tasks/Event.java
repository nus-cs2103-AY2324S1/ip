package com.alpha.tasks;

import java.time.LocalDateTime;

import com.alpha.enums.TagEnum;
import com.alpha.utils.Parser;

/**
 * The type Event.
 */
public class Event extends Task {

    private final LocalDateTime start;

    private final LocalDateTime end;

    /**
     * Instantiates a new Event.
     *
     * @param name  Name of the task.
     * @param start Start datetime of the task.
     * @param end   End datetime of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.setTag(TagEnum.EVENT);
        this.start = Parser.parseDateTimeString(start);
        this.end = Parser.parseDateTimeString(end);
    }

    /**
     * Gets the start datetime of the task to display.
     *
     * @return Start datetime of the task in display format.
     */
    public String getStartToDisplay() {
        return Parser.parseDateTimeObjectToDisplay(start);
    }

    /**
     * Gets the start datetime of the task to store.
     *
     * @return Start datetime of the task in storage format.
     */
    public String getStartToStore() {
        return Parser.parseDateTimeObjectToStore(start);
    }

    /**
     * Gets the end datetime of the task to display.
     *
     * @return End datetime of the task in display format.
     */
    public String getEndToDisplay() {
        return Parser.parseDateTimeObjectToDisplay(end);
    }

    /**
     * Gets the end datetime of the task to store.
     *
     * @return end datetime of the task in storage format.
     */
    public String getEndToStore() {
        return Parser.parseDateTimeObjectToStore(end);
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + getStartToDisplay() + " to: " + getEndToDisplay() + ")";
    }
}
