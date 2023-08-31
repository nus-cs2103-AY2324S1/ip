package TaskPackages;

public class Task {
  protected String description;
  protected boolean doneness;

  protected Task(String description, boolean doneness) {
    this.description = description;
    this.doneness = doneness;
  }

  protected String getStatusIcon() {
    return (doneness ? "X" : " ");
  }

  protected boolean getIsDone() {
    return this.doneness;
  }

  protected void setDoneness(boolean doneness) {
    this.doneness = doneness;
  }

  public String toString() {
    return String.format("[ ][%s] %s", this.getStatusIcon(), this.description);
  }

  public String toFileString() {
    return String.format("  # %d # %s", (doneness ? 1 : 0), this.description);
  }
}
