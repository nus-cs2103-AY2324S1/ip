abstract class Task {
  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void setDone() {
    this.isDone = true;
  }

  public void setUndone() {
    this.isDone = false;
  }

  private String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }
}