public class Task {
  private String name;
  private boolean done;

  public Task(String name) {
    this.name = name;
  }

  public void markDone() {
    this.done = true;
  }

  public void unmarkDone() {
    this.done = false;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.done ? "X" : " ", this.name);
  }
}
