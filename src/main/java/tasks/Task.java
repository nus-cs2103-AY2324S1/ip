package tasks;

public class Task {
  private Boolean isDone = false;
  private final String description;

  public Task(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "[" + (isDone ? "X" : " ") + "] " + description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }
}
