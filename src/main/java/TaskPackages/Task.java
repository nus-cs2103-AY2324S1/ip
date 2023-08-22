package TaskPackages;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  protected String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  protected boolean getIsDone() {
    return this.isDone;
  }

  protected void setDoneness(boolean isDone) {
    this.isDone = isDone;
  }

  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }
}
