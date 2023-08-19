public class Task {
  private boolean mark = false;
  private String name;

  public Task(String name) {
    this.name = name;
  }

  public void mark() {
    mark = true;
  }

  public void unmark() {
    mark = false;
  }

  @Override
  public String toString() {
    return String.format("[%c] %s", mark ? 'X' : ' ', name);
  }
}
