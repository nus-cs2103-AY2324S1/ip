package com.cloud.chatbot.item;



/**
 * Represents all work Item types.
 */
public enum ItemType {
    TASK("T"),
    DEADLINE("D"),
    EVENT("E"),
    UNKNOWN("?");

    /** The type string. */
    public String string;

    private ItemType(String _string) {
        this.string = _string;
    }

    /**
     * Returns the matching ItemType for the specified type string.
     *
     * @param string The type string.
     */
    public static ItemType fromString(String string) {
        switch (string) {
        case "T":
            return ItemType.TASK;
        case "D":
            return ItemType.DEADLINE;
        case "E":
            return ItemType.EVENT;
        default:
            return ItemType.UNKNOWN;
        }
    }
}
