public abstract class Task {
  private boolean mark = false;
  private final String description;
  private final char type;

  public Task(String description, char type) throws DukeException {
    this.description = description;
    this.type = type;

    if (description.isEmpty())
      throw new DukeException(
          String.format(
              "The description of a %s cannot be empty.",
              this.getClass().getSimpleName().toLowerCase()));
  }

  public void mark() {
    mark = true;
  }

  public void unmark() {
    mark = false;
  }

  @Override
  public String toString() {
    return String.format("  [%c][%c] %s", type, mark ? 'X' : ' ', description);
  }
}
