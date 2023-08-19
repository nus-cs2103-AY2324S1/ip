public class Todo extends Task {

  public Todo(String taskString) throws HoroException {
    super(taskString);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
