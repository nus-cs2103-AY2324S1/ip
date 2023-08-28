package TaskPackages;

public class Todo extends Task{

  protected Todo(String description) {
    super(description);
  }
  
  @Override
  public String toString() {
    return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
  }

  @Override
  public String toFileString() {
    return String.format("T # %d # %s", (isDone ? 1 : 0), this.description);
  }
}
