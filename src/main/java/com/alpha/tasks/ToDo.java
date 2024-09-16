package com.alpha.tasks;

import com.alpha.enums.TagEnum;

/**
 * The type To do.
 */
public class ToDo extends Task {

    /**
     * Instantiates a new To do.
     *
     * @param name The name of the task.
     */
    public ToDo(String name) {
        super(name);
        this.setTag(TagEnum.TODO);
    }

    @Override
    public String toStorageString() {
        return "todo " + getName();
    }
}
