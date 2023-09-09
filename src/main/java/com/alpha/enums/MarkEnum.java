package com.alpha.enums;

/**
 * The enum Mark enum.
 */
public enum MarkEnum {

    /**
     * Done mark enum.
     */
    DONE("X"),
    /**
     * Notdone mark enum.
     */
    NOTDONE(" ");

    private final String mark;

    MarkEnum(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return this.mark;
    }
}
