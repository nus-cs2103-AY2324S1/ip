abstract class Task {
  private String description;
  private Boolean isDone;
  protected String symbol;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void setDone() {
    this.isDone = true;
  }

  public void setUndone() {
    this.isDone = false;
  }

  private String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  protected String save() {
    return String.format("%d|%s", this.isDone.compareTo(false),
        this.description);
  };

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }
}