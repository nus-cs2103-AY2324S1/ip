package com.alpha.tasks;

import com.alpha.enums.TagEnum;

public class Event extends Task {

  private final String start;

  private final String end;

  public Event(String name, String start, String end) {
    super(name);
    this.setTag(TagEnum.EVENT);
    this.start = start;
    this.end = end;
  }

  public String getStart() {
    return this.start;
  }

  public String getEnd() {
    return end;
  }

  @Override
  public String toString() {
    return super.toString() + "(from: " + start + " to: " + end + ")";
  }
}
