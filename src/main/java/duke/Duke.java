package duke;

import exceptions.ParserException;
import io.Parser;
import io.Ui;
import java.util.NoSuchElementException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * The Duke class is the main entrypoint for the chatbot. It contains the main logic.
 */
public class Duke {

  private TaskList taskList;
  private Parser parser;
  private Ui ui;
  private Storage storage;

  public Duke() {
    this.taskList = new TaskList();
    this.parser = new Parser();
    this.ui = new Ui();
    this.storage = new Storage(this.taskList);
  }


  public void listTasks() {

    if (taskList.isEmpty()) {
      System.out.println("list is empty!");
      return;
    }

    for (int i = 0; i < taskList.size(); i++) {

      String index = Integer.toString(i + 1);
      Task selectedTask = taskList.get(i);
      System.out.println(index + " " + ui.displayTask(selectedTask));

    }
  }

  public void unmarkTask() {
    try {
      // set current task as un-done
      Task selectedTask = taskList.get(parser.getIndex());
      selectedTask.setUnDone();
      ui.displayAction("Marked selected task as un-done desu", selectedTask);
    } catch (IndexOutOfBoundsException ex) {
      System.out.println("Please enter a valid index!");
    }
  }

  public void markTaskAsDone() {
    try {
      // set current task as done
      Task selectedTask = taskList.get(parser.getIndex());
      selectedTask.setDone();
      ui.displayAction("Marked selected task as done", selectedTask);
    } catch (IndexOutOfBoundsException ex) {
      System.out.println("Please enter a valid index!");
    }
  }

  public void addTodo() {
    try {
      Task curentTask = new Todo(parser.getTaskName());
      taskList.add(curentTask);
      System.out.println("added:\t" + ui.displayTask(curentTask));
    } catch (StringIndexOutOfBoundsException ex) {

      System.out.println("Please enter a name after the todo command!");
    }
  }

  public void addDeadline() {

    try {
      Task curentTask = parser.parseDeadline();
      taskList.add(curentTask);
      System.out.println("added:\t" + ui.displayTask(curentTask));
    } catch (ParserException ex) {
      System.out.println(ex.getMessage());
    }


  }

  public void addEvent() {
    try {
      Task curentTask = parser.parseEvent();
      taskList.add(curentTask);
      System.out.println("added:\t" + ui.displayTask(curentTask));
    } catch (ParserException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void deleteTask() {
    if (taskList.isEmpty()) {
      System.out.println("The list is empty!");
      return;
    }
    try {
      // remove the current task
      Task selectedTask = taskList.get(parser.getIndex());
      taskList.remove(parser.getIndex());
      ui.displayAction("Deleting selected task!", selectedTask);
    } catch (IndexOutOfBoundsException ex) {
      System.out.println("Please enter a valid index!");
    }
  }

  public void run() {

    ui.displayGreetings();
    storage.loadTasks();

    label:
    while (true) {

      try {
        parser.update();
      } catch (NoSuchElementException ex) {
        break;
      }

      // there is no input
      if (parser.isInputThere()) {
        break;
      }

      switch (parser.getCommandString()) {
        case "bye":
          break label;
        case "list":
          listTasks();
          break;
        case "mark": {
          markTaskAsDone();
          break;
        }
        case "unmark": {
          unmarkTask();
          break;
        }
        case "todo": {
          addTodo();
          break;
        }
        case "deadline": {
          addDeadline();
          break;
        }
        case "event": {
          addEvent();
          break;
        }
        case "delete": {
          deleteTask();
          break;
        }
        default:
          System.out.println("Please enter a suitable task!");
      }
    }

    ui.displayGoodbye();
    storage.saveTasks();


  }

  public static void main(String[] args) {

    Duke duke = new Duke();
    duke.run();

  }
}
