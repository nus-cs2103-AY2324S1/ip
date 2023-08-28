package tasks;

import com.google.gson.annotations.SerializedName;

public class ToDo extends Task {
  @SerializedName("type")
  private static final String TYPE = "todo";
  public ToDo(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return String.format("[T] %s", super.toString());
  }
}
