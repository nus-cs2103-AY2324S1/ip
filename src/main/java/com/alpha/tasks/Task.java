package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;


public class Task {

    private final String name;
    private TagEnum tag;
    private MarkEnum mark;

    public Task(String name) {
        this.tag = TagEnum.EMPTY;
        this.mark = MarkEnum.NOTDONE;
        this.name = name;
    }

    public TagEnum getTag() {
        return tag;
    }

    public void setTag(TagEnum tag) {
        this.tag = tag;
    }

    public String getTagName() {
        if (tag == TagEnum.TODO) {
            return "todo";
        } else if (tag == TagEnum.DEADLINE) {
            return "deadline";
        } else {
            return "event";
        }
    }

    public MarkEnum getMark() {
        return mark;
    }

    public void setMark(MarkEnum mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String wrap(String text) {
        return "[" + text + "]";
    }

    @Override
    public String toString() {
        return wrap(this.tag.getTag()) + wrap(this.mark.getMark()) + " " + this.name;
    }
}
