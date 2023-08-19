public class ToDoTask  extends Task {
  public ToDoTask(String tasName) {
    super(tasName);
  }

  public String toString() {
    return "[T]" + super.toString();
  }
}
