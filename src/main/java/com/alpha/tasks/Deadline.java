package com.alpha.tasks;

import java.time.LocalDateTime;

import com.alpha.enums.TagEnum;
import com.alpha.utils.DateTimeParser;

/**
 * The Deadline task.
 */
public class Deadline extends Task {

    private final LocalDateTime end;

    /**
     * Instantiates a new Deadline task.
     *
     * @param name The name of the task.
     * @param end  The end datetime as a LocalDateTime object.
     */
    public Deadline(String name, LocalDateTime end) {
        super(name);
        this.setTag(TagEnum.DEADLINE);
        this.end = end;
    }

    @Override
    public String toStorageString() {
        return "deadline "
                + getName()
                + " /by "
                + DateTimeParser.parseDateTimeObjectToStore(end);
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + DateTimeParser.parseDateTimeObjectToDisplay(end)
                + ")";
    }
}
