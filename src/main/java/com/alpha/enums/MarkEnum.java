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

    /**
     * Gets the mark of the task representing whether the task is completed or not.
     *
     * @return Mark of the task.
     */
    public String getMark() {
        return this.mark;
    }
}
