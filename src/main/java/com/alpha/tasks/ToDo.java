package com.alpha.tasks;

import com.alpha.enums.TagEnum;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
        this.setTag(TagEnum.TODO);
    }
}
