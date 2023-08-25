package horo.data;

import horo.HoroException;

public abstract class Task {
  private String description = "";
  private boolean isDone = false;

  public Task(String description) throws HoroException {
    if (description == null || description.isBlank()) {
      throw new HoroException("Task description cannot be empty");
    }

    this.description = description;
  }

  public void markDone() {
    this.isDone = true;
  }

  public void markNotDone() {
    this.isDone = false;
  }

  public Boolean isDone() {
    return isDone;
  }

  public String getDescription() {
    return this.description;
  }

  public abstract String getDataString();

  @Override
  public String toString() {
    return (isDone ? "[X] " : "[ ] ") + getDescription();
  }
}
