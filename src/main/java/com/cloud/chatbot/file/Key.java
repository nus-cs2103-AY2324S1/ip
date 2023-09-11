package com.cloud.chatbot.file;



/**
 * Represents all JSON keys used to represent work Items.
 */
public enum Key {
    TYPE("type"),
    DESCRIPTION("description"),
    IS_COMPLETE("isComplete"),

    START("start"),
    END("end");

    /** The key string. */
    public String string;

    private Key(String _string) {
        this.string = _string;
    }
}
