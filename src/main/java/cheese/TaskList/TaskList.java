package cheese.TaskList;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cheese.Task.Task;

public class TaskList {
  private List<Task> cheeseList = new ArrayList<>();

  public TaskList() {
  }

  public void addTask(Task task) {
    cheeseList.add(task);
  }

  public void deleteTask(int index) {
    cheeseList.remove(index);
  }

  public Task getTask(int index) {
    return cheeseList.get(index);
  }

  public int getSize() {
    return cheeseList.size();
  }

  public List<Task> getCheeseList() {
    return cheeseList;
  }

}



