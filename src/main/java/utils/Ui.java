package utils;

import tasks.Task;

public class Ui {
  public void hello() {
    System.out.println("Hello! I'm Jerma.");
  }

  public void bye() {
    System.out.println("See ya soon!");
  }

  public void error(String message) {
    System.out.println("Error: " + message);
  }

  public void listTasks(TaskList tasks) {
    System.out.println(tasks);
  }

  public void newTask(Task task) {
    System.out.println(String.format("Added %s: %s", task.getClass(), task));
  }

  public void markTask(Task task) {
    System.out.println("Marked as done: \n" + task);
  }

  public void unmarkTask(Task task) {
    System.out.println("Marked as undone: \n" + task);
  }

  public void deleteTask(Task task, int numberOfTasksRemaining) {
    System.out.println(
        String.format("Removed the task: \n%s \nYou have %d tasks remaining.",
            task, numberOfTasksRemaining));
  }
}
