package TaskPackages;

public class Task {
  protected String description;
  protected boolean isDone;

  protected Task(String description) {
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
    return String.format("[ ][%s] %s", this.getStatusIcon(), this.description);
  }

  public String toFileString() {
    return String.format("  # %d # %s", (isDone ? 1 : 0), this.description);
  }
}
