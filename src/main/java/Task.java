public class Task {
  private String taskString = "";
  private boolean isDone = false;

  public Task(String taskString) {
    this.taskString = taskString;
  }

  public void markDone() {
    this.isDone = true;
  }

  public void markNotDone() {
    this.isDone = false;
  }

  public String getTaskString() {
    return this.taskString;
  }

  @Override
  public String toString() {
    return (isDone ? "[X] " : "[ ] ") + getTaskString();
  }
}
