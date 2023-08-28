package tasks;

import com.google.gson.annotations.SerializedName;

public class Event extends Task {
  @SerializedName("type")
  private final static String TYPE = "event";
  private final String from;
  private final String to;

  public Event(String name, String from, String to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return String.format("[E] %s (from: %s to: %s)", super.toString(), this.from, this.to);
  }
}

