package com.alpha.enums;

/**
 * The enum Tag enum.
 */
public enum TagEnum {
    /**
     * Todo tag enum.
     */
    TODO("T"),
    /**
     * Deadline tag enum.
     */
    DEADLINE("D"),
    /**
     * Event tag enum.
     */
    EVENT("E"),
    /**
     * Empty tag enum.
     */
    EMPTY(" ");

    private final String tag;

    TagEnum(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return this.tag;
    }
}
