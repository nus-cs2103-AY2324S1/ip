package horo.data.tasks;

import horo.HoroException;

public class Todo extends Task {

  public Todo(String description) throws HoroException {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public String getDataString() {
    return "T," + (super.isDone() ? "1" : "0") + "," + super.getDescription();
  }
}
