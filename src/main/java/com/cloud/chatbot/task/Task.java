package com.cloud.chatbot.task;



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
        return "T";
    }
}
