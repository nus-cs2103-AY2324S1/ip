package com.cloud.chatbot.item;

import org.json.JSONObject;

import com.cloud.chatbot.file.Key;



/**
 * Represents a deadline work Item, which has an ending timestamp.
 */
public class Deadline extends Item {
    private String timestampEnd;

    /**
     * @param _description The Item description.
     * @param end The ending timestamp.
     */
    public Deadline(String _description, String end) {
        super(_description);

        this.timestampEnd = end;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | BY %s",
            this.getBasicString(number),
            this.getEnd()
        );
    }

    @Override
    public String getTypeString() {
        return ItemType.DEADLINE.string;
    }

    @Override
    public JSONObject export() {
        JSONObject json = this.getBasicJson();
        json.put(Key.END.string, this.getEnd());
        return json;
    }

    public String getEnd() {
        return this.timestampEnd;
    }
}
