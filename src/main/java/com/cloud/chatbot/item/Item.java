package com.cloud.chatbot.item;

import org.json.JSONObject;



/**
 * A parent work Item class.
 */
public abstract class Item {
    private boolean isComplete = false;

    private String description;

    public Item(String _description) {
        this.description = _description;
    }

    protected String getBasicString(int number) {
        return String.format(
            "%s | %s#%d: %s",
            this.getCompletionString(),
            this.getTypeString(),
            number,
            this.getDescription()
        );
    }

    protected JSONObject getBasicJson() {
        JSONObject json = new JSONObject();
        json.put("type", this.getTypeString());
        json.put("description", this.getDescription());
        json.put("isComplete", this.isComplete());
        return json;
    }

    public String toString(int number) {
        return this.getBasicString(number);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Returns a string denoting whether the Item has been completed.
     */
    public String getCompletionString() {
        return this.isComplete ? "X" : " ";
    }

    public void setComplete(boolean _isComplete) {
        this.isComplete = _isComplete;
    }

    /**
     * Returns a string representing the type of this Item.
     */
    public abstract String getTypeString();

    /*
     * Returns the JSON representation of this item.
     */
    public abstract JSONObject export();
}
