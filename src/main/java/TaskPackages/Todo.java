package TaskPackages;

public class Todo extends Task{

  protected Todo(String description) {
    super(description);
  }
  
  @Override
  public String toString() {
    return "[T][" + this.getStatusIcon() + "] " + this.description;
  }
}
