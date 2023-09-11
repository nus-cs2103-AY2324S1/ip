package com.cloud.chatbot.item;

import java.time.Instant;

import org.json.JSONObject;

import com.cloud.chatbot.DateConverter;
import com.cloud.chatbot.exceptions.IllegalTimestampException;
import com.cloud.chatbot.file.Key;



/**
 * Represents an event work Item, which has both a start and end Instant.
 */
public class Event extends Deadline {
    private Instant start;

    /**
     * @param _description The Item description.
     * @param _start The start Instant.
     * @param _end The end Instant.
     */
    public Event(String _description, Instant _start, Instant _end) throws IllegalTimestampException {
        super(_description, _end);

        if (_end.isBefore(_start)) {
            throw new IllegalTimestampException();
        }

        this.start = _start;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | FROM %s | TO %s",
            this.getBasicString(number),
            DateConverter.instantToPrettyTimestamp(this.getStart()),
            DateConverter.instantToPrettyTimestamp(this.getEnd())
        );
    }

    @Override
    public String getTypeString() {
        return ItemType.EVENT.string;
    }

    @Override
    public JSONObject export() {
        JSONObject json = this.getBasicJson();
        json.put(
            Key.START.string,
            this.getStart().toEpochMilli()
        );
        json.put(
            Key.END.string,
            this.getEnd().toEpochMilli()
        );
        return json;
    }

    public Instant getStart() {
        return this.start;
    }
}
