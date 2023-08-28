public class Task {

  private String description;
  private boolean done;

  public Task(String desc) {
    this.description = desc;
    this.done = false;

  }

  public void setDone() {
    this.done = true;
  }

  public void setUnDone() {
    this.done = false;
  }

  public String getDoneIcon() {
    return this.done ? "X" : " ";
  }

  public String getDescription() {
    return this.description;
  }

}
