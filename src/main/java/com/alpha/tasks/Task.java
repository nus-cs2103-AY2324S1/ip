package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;


public class Task {

  private TagEnum tag;

  private MarkEnum mark;

  private final String name;

  public Task(String name) {
    this.tag = TagEnum.EMPTY;
    this.mark = MarkEnum.NOTDONE;
    this.name = name;
  }

  public String getTag() {
    if (tag == TagEnum.TODO) {
      return "todo";
    } else if (tag == TagEnum.DEADLINE) {
      return "deadline";
    } else {
      return "event";
    }
  }

  public void setTag(TagEnum tag) {
    this.tag = tag;
  }

  public String getMark() {
    if (mark == MarkEnum.DONE) {
      return "1";
    } else {
      return "0";
    }
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
