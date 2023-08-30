package tasks;

public class ToDo extends Task {

  public ToDo(String des) {
    super(des);
  }

  public ToDo(String des, boolean mark) {
    super(des, mark);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
