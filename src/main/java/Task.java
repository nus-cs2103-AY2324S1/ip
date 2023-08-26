public abstract class Task {
  private String taskName;

  private boolean isCompleted;

  public Task(String taskName) {
    this.taskName = taskName;
  }

  public Task(String taskName, boolean isCompleted) {
    this.taskName = taskName;
    this.isCompleted = isCompleted;
  }


  public abstract String saveData();

  public void markCompleted() {
    this.isCompleted = true;
  }

  public void markNotCompleted() {
    this.isCompleted = false;
  }

  protected boolean isCompleted() {
    return this.isCompleted;
  }

  protected String taskName() {
    return this.taskName;
  }


  public String toString() {
    if (isCompleted) {
      return "[X]" + taskName;
    } else {
      return "[ ]" + taskName;
    }
  }
}
