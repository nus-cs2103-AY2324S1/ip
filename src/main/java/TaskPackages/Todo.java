package TaskPackages;

public class Todo extends Task {

  public Todo(String description, boolean doneness) {
    super(description, doneness);
  }

  @Override
  public String toString() {
    return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
  }

  @Override
  public String toFileString() {
    return String.format("T # %d # %s", (doneness ? 1 : 0), this.description);
  }
}
