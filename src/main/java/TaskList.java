import java.util.ArrayList;
import java.util.List;

public class TaskList {

  private final List<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public void addTask(String task) {
    System.out.println("Got it. I've added this task:");
    Task currentTask;
    switch (Parser.getTask(task)) {
      case "todo":
        currentTask = new ToDo(Parser.getToDoName(task));
        break;
      case "deadline":
        currentTask = new Deadline(Parser.getDeadlineName(task), Parser.getDeadlineEnd(task));
        break;
      case "event":
        currentTask = new Event(Parser.getEventName(task), Parser.getEventStart(task),
            Parser.getEventEnd(task));
        break;
      default:
        currentTask = new Task(task);
    }
    tasks.add(currentTask);
    System.out.println(currentTask);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  public void printTasks() {
    System.out.println("Here are the tasks in your list:");
    int count = 1;
    for (Task task : tasks) {
      String row = count++ + "." + task.toString();
      System.out.println(row);
    }
  }

  public void markTask(int taskNumber) {
    System.out.println("Nice! I've marked this task as done:");
    Task task = tasks.get(taskNumber - 1);
    task.setMark("[X]");
    System.out.println(task);
  }

  public void unmarkTask(int taskNumber) {
    System.out.println("OK, I've marked this task as not done yet:");
    Task task = tasks.get(taskNumber - 1);
    task.setMark("[ ]");
    System.out.println(task);
  }
}
