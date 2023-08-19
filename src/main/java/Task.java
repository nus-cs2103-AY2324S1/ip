import java.util.ArrayList;

public class Task {
  public static ArrayList<Task> taskList = new ArrayList<>();
  private String taskName;

  public Task(String taskName) {
    this.taskName = taskName;
  }

  public static void addTask(Task task) {
    taskList.add(task);
    Duke.printLine();
    System.out.printf("added: %s\n%n", task.getTaskName());
    Duke.printLine();
  }

  public static void listTasks() {
    Duke.printLine();
    for (int i = 0; i < taskList.size(); i++) {
      System.out.printf("%s. %s%n", i + 1, taskList.get(i).getTaskName());
    }
    System.out.println();
    Duke.printLine();
  }

  public String getTaskName() {
    return this.taskName;
  }
}
