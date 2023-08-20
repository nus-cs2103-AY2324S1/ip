public class Todo extends Task {
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  Todo(String taskName) {
    super(taskName);
  }
}
