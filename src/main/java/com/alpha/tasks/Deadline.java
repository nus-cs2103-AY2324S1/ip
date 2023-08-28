package com.alpha.tasks;

import java.time.LocalDateTime;

import com.alpha.enums.TagEnum;
import com.alpha.utils.Parser;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    private final LocalDateTime end;

    /**
     * Instantiates a new Deadline.
     *
     * @param name Name of the task.
     * @param end  End datetime of the task.
     */
    public Deadline(String name, String end) {
        super(name);
        this.setTag(TagEnum.DEADLINE);
        this.end = Parser.parseDateTimeString(end);
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
     * Gets the end datetime of the task to storage.
     *
     * @return End datetime of the task in storage format.
     */
    public String getEndToStore() {
        return Parser.parseDateTimeObjectToStore(end);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getEndToDisplay() + ")";
    }
}
