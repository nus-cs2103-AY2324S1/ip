public class Task {

  private String status;

  private String name;

  public Task(String name) {
    this.status = "[ ]";
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }
}
