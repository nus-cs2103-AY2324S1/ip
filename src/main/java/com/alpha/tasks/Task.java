package com.alpha.tasks;

public class Task {

  private String tag;

  private String mark;

  private final String name;

  public Task(String name) {
    this.tag = "[ ]";
    this.mark = "[ ]";
    this.name = name;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  @Override
  public String toString() {
    return this.tag + this.mark + " " + this.name;
  }
}
