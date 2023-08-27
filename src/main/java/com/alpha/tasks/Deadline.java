package com.alpha.tasks;

import com.alpha.enums.TagEnum;

public class Deadline extends Task {

  private final String end;

  public Deadline(String name, String end) {
    super(name);
    this.setTag(TagEnum.DEADLINE);
    this.end = end;
  }

  public String getEnd() {
    return end;
  }

  @Override
  public String toString() {
    return super.toString() + " (by: " + end + ")";
  }
}
