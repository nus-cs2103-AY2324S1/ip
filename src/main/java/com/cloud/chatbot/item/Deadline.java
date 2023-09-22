package com.cloud.chatbot.item;

import java.time.Instant;

import org.json.JSONObject;

import com.cloud.chatbot.DateConverter;
import com.cloud.chatbot.file.Key;



/**
 * Represents a deadline work Item, which has an end Instant.
 */
public class Deadline extends Item {
    private Instant end;

    /**
     * @param _description The Item description.
     * @param _end The end Instant.
     */
    public Deadline(String _description, Instant _end) {
        super(_description);

        this.end = _end;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | BY %s",
            this.getBasicString(number),
            DateConverter.instantToPrettyTimestamp(this.getEnd())
        );
    }

    @Override
    public String getTypeString() {
        return ItemType.DEADLINE.string;
    }

    @Override
    public JSONObject export() {
        JSONObject json = this.getBasicJson();
        json.put(
            Key.END.string,
            this.getEnd().toEpochMilli()
        );
        return json;
    }

    public Instant getEnd() {
        return this.end;
    }
}
