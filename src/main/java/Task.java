public abstract class Task {
  private boolean mark = false;
  private final String name;
  private final char type;

  public Task(String name, char type) {
    this.name = name;
    this.type = type;
  }

  public void mark() {
    mark = true;
  }

  public void unmark() {
    mark = false;
  }

  @Override
  public String toString() {
    return String.format("[%c][%c] %s", type, mark ? 'X' : ' ', name);
  }
}
