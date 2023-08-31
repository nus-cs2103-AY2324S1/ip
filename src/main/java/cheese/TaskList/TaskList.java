package cheese.TaskList;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cheese.Task.Task;


/**
 * Represents a list of tasks.
 */
public class TaskList {
  private List<Task> cheeseList = new ArrayList<>();

  /**
   * Adds a task to the list.
   * @param task Task to be added.
   */
  public void addTask(Task task) {
    cheeseList.add(task);
  }

  /**
   * Adds a task to the list.
   * @param task Task to be added.
   */
  public void deleteTask(int index) {
    cheeseList.remove(index);
  }

  /**
   * Marks a task as done.
   * @param index Index of task to be marked as done.
   * @return Task .
   */
  public Task getTask(int index) {
    return cheeseList.get(index);
  }


  /**
   * Marks a task as done.
   * @param index Index of task to be marked as done.
   * @return Task .
   */
  public int getSize() {
    return cheeseList.size();
  }

  /**
   * Marks a task as done.
   * @param index Index of task to be marked as done.
   * @return Task .
   */
  public List<Task> getCheeseList() {
    return cheeseList;
  }

}



