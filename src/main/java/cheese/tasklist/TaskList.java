package cheese.tasklist;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cheese.task.Task;


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
    assert cheeseList.size() > 0 : "TaskList should not be empty";
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
    assert index >= 0 : "Index should be positive";
    assert index < cheeseList.size() : "Index should be less than size of list";
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
  
  public TaskList filterTasks(String kw) {
    TaskList filteredList = new TaskList();
    for (Task task : cheeseList) {
      if (task.isInDescripton(kw)) {
        filteredList.addTask(task);
      }
    }
    return filteredList;
  }
}



