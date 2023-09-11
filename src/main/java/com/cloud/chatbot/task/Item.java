package com.cloud.chatbot.task;



/**
 * A parent work Item class.
 */
public abstract class Item {
    private boolean isComplete = false;

    private String description;

    /**
     * @param _description The Item description.
     */
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
}
