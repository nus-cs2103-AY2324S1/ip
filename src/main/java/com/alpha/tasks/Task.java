package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;


/**
 * The type Task.
 */
public abstract class Task {

    private final String name;
    private TagEnum tag;
    private MarkEnum mark;

    /**
     * Instantiates a new Task.
     *
     * @param name the name
     */
    public Task(String name) {
        this.tag = TagEnum.EMPTY;
        this.mark = MarkEnum.NOTDONE;
        this.name = name;
    }

    /**
     * Returns the task as a string to be stored in the local storage file.
     *
     * @return The string representation of the task.
     */
    public abstract String toStorageString();

    /**
     * Sets the tag of the task.
     *
     * @param tag The tag of the task.
     */
    public void setTag(TagEnum tag) {
        this.tag = tag;
    }

    /**
     * Gets the mark of the task.
     *
     * @return The mark of the task.
     */
    public MarkEnum getMark() {
        return mark;
    }

    /**
     * Sets the mark of the task.
     *
     * @param mark The mark of the task.
     */
    public void setMark(MarkEnum mark) {
        this.mark = mark;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    private String wrap(String text) {
        return "[" + text + "]";
    }

    @Override
    public String toString() {
        return wrap(this.tag.toString()) + wrap(this.mark.toString()) + " " + this.name;
    }
}
