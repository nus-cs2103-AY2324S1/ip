package com.alpha.tasks;

import java.time.LocalDateTime;

import com.alpha.enums.TagEnum;
import com.alpha.utils.Parser;

public class Event extends Task {

    private final LocalDateTime start;

    private final LocalDateTime end;

    public Event(String name, String start, String end) {
        super(name);
        this.setTag(TagEnum.EVENT);
        this.start = Parser.parseDateTimeString(start);
        this.end = Parser.parseDateTimeString(end);
    }

    public String getStartToDisplay() {
        return Parser.parseDateTimeObjectToDisplay(start);
    }

    public String getStartToStore() {
        return Parser.parseDateTimeObjectToStore(start);
    }

    public String getEndToDisplay() {
        return Parser.parseDateTimeObjectToDisplay(end);
    }

    public String getEndToStore() {
        return Parser.parseDateTimeObjectToStore(end);
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + getStartToDisplay() + " to: " + getEndToDisplay() + ")";
    }
}
