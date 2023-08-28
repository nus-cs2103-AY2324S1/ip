package tasks;

import com.google.gson.annotations.SerializedName;

public class Deadline extends Task {
  @SerializedName("type")
  private final static String TYPE = "deadline";
  @SerializedName("due")
  private final String deadline;

  public Deadline(String name, String deadline) {
    super(name);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
  }
}
