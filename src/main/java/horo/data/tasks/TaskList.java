package horo.data.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import horo.data.HoroList;

public class TaskList extends HoroList<Task> {

  public TaskList() {
    super();
  }

  public TaskList(ArrayList<Task> taskList) {
    super(taskList);
  }

  public void add(Task newTask) {
    list.add(newTask);
    System.out.println("Added: ");
    System.out.println(newTask);
  }

  public void remove(int i) {
    if (list.isEmpty()) {
      System.out.println("No tasks available");
      return;
    }

    try {
      Task t = list.remove(i);
      System.out.println("Removed task: ");
      System.out.println(t);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Please enter a valid number from 1 - " + list.size());
    }
  }

  public void markTaskDone(int i) {
    if (list.isEmpty()) {
      System.out.println("No tasks available");
      return;
    }

    try {
      list.get(i).markDone();
      System.out.println("Task marked as done");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Please enter a valid number from 1 - " + list.size());
    }
  }

  public void markTaskNotDone(int i) {
    if (list.isEmpty()) {
      System.out.println("No tasks available");
      return;
    }

    try {
      list.get(i).markNotDone();
      System.out.println("Task marked as not done");
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Please enter a valid number from 1 - " + list.size());
    }
  }

  public ArrayList<Task> findTasks(Collection<String> keywords) {
    ArrayList<Task> matchedTasks = new ArrayList<>();
    for (Task task : list) {
      final Set<String> wordsInDescription = new HashSet<>(Arrays.asList(task.getDescription().split(" ")));
      if (!Collections.disjoint(wordsInDescription, keywords)) {
        matchedTasks.add(task);
      }
    }
    return matchedTasks;
  }

  public ArrayList<Task> getTasks() {
    return list;
  }
}
