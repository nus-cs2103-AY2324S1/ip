import java.util.ArrayList;

public class TaskList {

  private final ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public void addTask(String task) {
    tasks.add(new Task(task));
  }

  public void printTasks() {
    int count = 1;
    for (Task task : tasks) {
      System.out.println(
          count++ + "." + task.getStatus() + " " + task.getName());
    }
  }

  public void markTask(int taskNumber) {
    System.out.println("Nice! I've marked this task as done:");
    Task task = tasks.get(taskNumber - 1);
    task.setStatus("[X]");
    System.out.println(task.getStatus() + " " + task.getName());
  }

  public void unmarkTask(int taskNumber) {
    System.out.println("OK, I've marked this task as not done yet:");
    Task task = tasks.get(taskNumber - 1);
    task.setStatus("[ ]");
    System.out.println(task.getStatus() + " " + task.getName());
  }
}
