package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.exceptions.InvalidTaskException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

  private final List<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public TaskList(List<Task> tasks) {
    this.tasks = tasks;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public int getSize() {
    return tasks.size();
  }

  public Task markTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    task.setMark(MarkEnum.DONE);
    return task;
  }


  public Task unmarkTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    task.setMark(MarkEnum.NOTDONE);
    return task;
  }

  public Task deleteTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    tasks.remove(task);
    return task;
  }
}
