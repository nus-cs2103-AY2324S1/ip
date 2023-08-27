package com.alpha.tasks;

import com.alpha.enums.MarkEnum;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.exceptions.InvalidTaskException.UnknownTaskException;
import com.alpha.utils.Parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

  private final List<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
    loadData();
  }

  private void loadData() {
    try {
      new File("./data").mkdir();
      File localDataFile = new File("./data/save.txt");
      localDataFile.createNewFile();
      Scanner sc = new Scanner(localDataFile);
      int count = 1;
      while (sc.hasNextLine()) {
        addTaskSilent(sc.nextLine());
        if (Integer.parseInt(sc.nextLine()) == 1) {
          markTaskSilent(count);
        }
        ++count;
      }
    } catch (SecurityException | IOException | InvalidTaskException e) {
      System.out.println(e.getMessage());
    }
  }

  public void saveData() {
    new File("./data").mkdir();
    try {
      FileWriter fw = new FileWriter("./data/save.txt");
      boolean first = true;
      for (Task task : tasks) {
        StringBuilder textToAdd = new StringBuilder();
        if (!first) {
          textToAdd.append("\n");
        } else {
          first = false;
        }
        textToAdd.append(task.getTag());
        textToAdd.append(" ");
        textToAdd.append(task.getName());
        if (task instanceof Deadline) {
          textToAdd.append(" /by ");
          Deadline deadline = (Deadline) task;
          textToAdd.append(deadline.getEnd());
        } else if (task instanceof Event) {
          textToAdd.append(" /from ");
          Event event = (Event) task;
          textToAdd.append(event.getStart());
          textToAdd.append(" /to ");
          textToAdd.append(event.getEnd());
        }
        if (task.getMark() == "1") {
          textToAdd.append("\n1");
        } else {
          textToAdd.append("\n0");
        }
        fw.write(textToAdd.toString());
      }
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void addTask(String task) throws InvalidTaskException {
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
        throw new UnknownTaskException();
    }
    System.out.println("Got it. I've added this task:");
    tasks.add(currentTask);
    System.out.println(currentTask);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  public void addTaskSilent(String task) throws InvalidTaskException {
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
        throw new UnknownTaskException();
    }
    tasks.add(currentTask);
  }

  public void printTasks() {
    System.out.println("Here are the tasks in your list:");
    int count = 1;
    for (Task task : tasks) {
      String row = count++ + "." + task.toString();
      System.out.println(row);
    }
  }

  public void markTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    task.setMark(MarkEnum.DONE);
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(task);
  }

  public void markTaskSilent(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    task.setMark(MarkEnum.DONE);
  }

  public void unmarkTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    task.setMark(MarkEnum.NOTDONE);
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.println(task);
  }

  public void deleteTask(int taskNumber) throws InvalidTaskException {
    if (taskNumber > tasks.size()) {
      throw new InvalidTaskException("Task does not exist, please enter valid task number");
    }
    Task task = tasks.get(taskNumber - 1);
    tasks.remove(task);
    System.out.println("Noted. I've removed this task:");
    System.out.println(task);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }
}
