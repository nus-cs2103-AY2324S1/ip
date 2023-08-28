package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;


/**
 * The type Task.
 */
public class Task {

    private final String name;
    private TagEnum tag;
    private MarkEnum mark;

    /**
     * Instantiates a new Task.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.tag = TagEnum.EMPTY;
        this.mark = MarkEnum.NOTDONE;
        this.name = name;
    }

    /**
     * Gets the tag representing the task.
     *
     * @return Tag representing the task.
     */
    public TagEnum getTag() {
        return tag;
    }

    /**
     * Sets the tag representing the task.
     *
     * @param tag Tag representing the task.
     */
    public void setTag(TagEnum tag) {
        this.tag = tag;
    }

    /**
     * Gets the full tag name of the task.
     *
     * @return Full tag name of the task.
     */
    public String getTagName() {
        if (tag == TagEnum.TODO) {
            return "todo";
        } else if (tag == TagEnum.DEADLINE) {
            return "deadline";
        } else {
            return "event";
        }
    }

    /**
     * Gets the mark of the task.
     *
     * @return Mark of the task.
     */
    public MarkEnum getMark() {
        return mark;
    }

    /**
     * Sets the mark of the task.
     *
     * @param mark Mark of the task.
     */
    public void setMark(MarkEnum mark) {
        this.mark = mark;
    }

    /**
     * Gets name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Wrap string with [].
     *
     * @param text Text to be wrapped.
     * @return Wrapped string.
     */
    public String wrap(String text) {
        return "[" + text + "]";
    }

    @Override
    public String toString() {
        return wrap(this.tag.getTag()) + wrap(this.mark.getMark()) + " " + this.name;
    }
}
