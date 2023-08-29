package duke;
import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> taskList;

  public TaskList() {
    this.taskList = new ArrayList<Task>();
  }

  public TaskList(ArrayList<Task> savedTaskList) throws DukeException {
    // this.taskList.addAll(savedTaskList);
    this.taskList = savedTaskList;
  }

  public int size() {
    return taskList.size();
  }

  public void addToTaskList(Task task) {
    if (taskList.size() < 100) {
      taskList.add(task);
    } else {
      System.out.println("Error: List is full.");
    }
  }

  public String taskToString(int i) {
    return "\t" + (i + 1) + ". " + taskList.get(i);
  }

  @Override
  public String toString() {
    String outputString = "";
    for (int i = 0; i < taskList.size(); i++) {
      outputString += taskToString(i) + "\n";
    }

    return outputString;
  }

  public void markTaskAsDone(int taskIndex) {
    taskList.get(taskIndex).markAsDone();
  }

  public void unmarkTaskAsDone(int taskIndex) {
    taskList.get(taskIndex).unmarkAsDone();
  }

  public String getTaskItemSaveString(int i) {
    return taskList.get(i).saveString();
  }

  public void deleteTask(int taskIndex) {
    taskList.remove(taskIndex);
  }
}
