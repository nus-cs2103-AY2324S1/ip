package cyrus.tasks;

import com.google.gson.annotations.SerializedName;

public class Task {
  public final String name;
  @SerializedName("status")
  private boolean done;

  public Task(String name) {
    this.name = name;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.done ? "X" : " ", this.name);
  }
}
