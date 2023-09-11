package com.cloud.chatbot.item;

import org.json.JSONObject;

import com.cloud.chatbot.file.Key;



/**
 * Represents an event work Item, which has both a starting and ending timestamp.
 */
public class Event extends Deadline {
    private String timestampStart;

    /**
     * @param _description The Item description.
     * @param start The starting timestamp.
     * @param end The ending timestamp.
     */
    public Event(String _description, String start, String end) {
        super(_description, end);

        this.timestampStart = start;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | FROM %s | TO %s",
            this.getBasicString(number),
            this.getStart(),
            this.getEnd()
        );
    }

    @Override
    public String getTypeString() {
        return ItemType.EVENT.string;
    }

    @Override
    public JSONObject export() {
        JSONObject json = this.getBasicJson();
        json.put(Key.START.string, this.getStart());
        json.put(Key.END.string, this.getEnd());
        return json;
    }

    public String getStart() {
        return this.timestampStart;
    }
}
