package com.cloud.chatbot.item;

import org.json.JSONObject;



/**
 * Represents the most basic form of work Item.
 */
public class Task extends Item {
    /**
     * @param _description The Item description.
     */
    public Task(String _description) {
        super(_description);
    }

    @Override
    public String getTypeString() {
        return ItemType.TASK.string;
    }

    @Override
    public JSONObject export() {
        return this.getBasicJson();
    }
}
