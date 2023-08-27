package com.alpha.tasks;

import com.alpha.enums.TagEnum;
import com.alpha.utils.Parser;
import java.time.LocalDateTime;

public class Deadline extends Task {

  private final LocalDateTime end;

  public Deadline(String name, String end) {
    super(name);
    this.setTag(TagEnum.DEADLINE);
    this.end = Parser.parseDateTimeString(end);
  }

  public String getEndToDisplay() {
    return Parser.parseDateTimeObjectToDisplay(end);
  }

  public String getEndToStore() {
    return Parser.parseDateTimeObjectToStore(end);
  }

  @Override
  public String toString() {
    return super.toString() + " (by: " + getEndToDisplay() + ")";
  }
}
