public class ToDoTask  extends Task {
  public ToDoTask(String taskName) {
    super(taskName);
  }

  public ToDoTask(String taskName, boolean isCompleted) {
    super(taskName, isCompleted);
  }

  @Override
  public String saveData() {
    char delimiter = 31;
    String isCompleted = isCompleted() ? "1" : "0";
    return "todo" + delimiter + isCompleted + delimiter + taskName() + delimiter;
  }

  public String toString() {
    return "[T]" + super.toString();
  }
}
