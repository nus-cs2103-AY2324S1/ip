public class Task {
  private String taskName;

  private boolean isCompleted;

  public Task(String taskName) {
    this.taskName = taskName;
  }

  public void markCompleted() {
    this.isCompleted = true;
  }

  public void markNotCompleted() {
    this.isCompleted = false;
  }


  public String toString() {
    if (isCompleted) {
      return "[X]" + " " + taskName;
    } else {
      return "[ ]" + " " + taskName;
    }
  }
}
