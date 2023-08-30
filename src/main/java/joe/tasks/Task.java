package joe.tasks;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public String getDescription() {
    return description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsNotDone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
  }
}
