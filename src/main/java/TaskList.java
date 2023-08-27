import java.util.LinkedList;

public class TaskList {

  private LinkedList<Task> tasks = new LinkedList<>();

  public TaskList(LinkedList<Task> tasks) {
    this.tasks = tasks;
  }

  public LinkedList<Task> getTaskList() {
    return this.tasks;
  }

  public void listTasks() {
    int i = 1;
    System.out.println("     Here are the tasks in your list:");
    for (Task task : tasks) {
      System.out.println("     " + i + "." + task);
      i += 1;
    }
  }

  public void addTask(Task task) {
    tasks.add(task);
    System.out.println("     " + "Got it. I've added this task:");
    System.out.println("       " + task.toString());
    System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
  }

  public void deleteTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.remove(i);
      System.out.println("     Noted. I've removed this task:");
      System.out.println("       " + task.toString());
      System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  public void markTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }
      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markCompleted();
      System.out.println("     Nice! I've marked this task as done:");
      System.out.println("       " + task.toString());

    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }

  public void unmarkedTask(String index) throws WrongIndexException {
    try {
      String regex = "\\d+";
      if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
              || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
        throw new WrongIndexException();
      }

      int i = Integer.parseInt(index, 10) - 1;
      Task task = tasks.get(i);
      task.markNotCompleted();
      System.out.println("     OK, I've marked this task as not done yet:");
      System.out.println("       " + task.toString());
    } catch (NumberFormatException e) {
      throw new WrongIndexException();
    }
  }
}
