package com.alpha.tasks;

public class Deadline extends Task {

  private final String end;

  public Deadline(String name, String end) {
    super(name);
    this.setTag("[D]");
    this.end = end;
  }

  @Override
  public String toString() {
    return super.toString() + " (by: " + end + ")";
  }
}
