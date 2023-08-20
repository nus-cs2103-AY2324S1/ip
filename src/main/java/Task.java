public abstract class Task {
  protected boolean completed;
  protected String taskName;


  public void toggleComplete() {
    this.completed = !this.completed;
  }

  public boolean isCompleted() {
    return this.completed;
  }

  @Override
  public String toString() {
    return (this.completed ? "[X] " : "[ ] ") + this.taskName;
  }

  Task(String taskName) {
    this.taskName = taskName;
    this.completed = false;
  }
}
