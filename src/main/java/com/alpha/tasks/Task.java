package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.enums.TagEnum;
import javax.swing.text.html.HTML.Tag;

public class Task {

  private TagEnum tag;

  private MarkEnum mark;

  private final String name;

  public Task(String name) {
    this.tag = TagEnum.EMPTY;
    this.mark = MarkEnum.NOTDONE;
    this.name = name;
  }

  public void setTag(TagEnum tag) {
    this.tag = tag;
  }

  public void setMark(MarkEnum mark) {
    this.mark = mark;
  }

  public String wrap(String text) {
    return "[" + text + "]";
  }

  @Override
  public String toString() {
    return wrap(this.tag.getTag()) + wrap(this.mark.getMark()) + " " + this.name;
  }
}
