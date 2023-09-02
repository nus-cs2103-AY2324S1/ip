import exceptions.ParserException;
import io.Parser;
import io.Ui;
import java.util.NoSuchElementException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

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

          if (taskList.isEmpty()) {
            System.out.println("list is empty!");
            break;
          }

          for (int i = 0; i < taskList.size(); i++) {

            String index = Integer.toString(i + 1);
            Task selectedTask = taskList.get(i);
            System.out.println(index + " " + ui.displayTask(selectedTask));

          }
          break;
        case "mark": {
          try {
            // set current task as done
            Task selectedTask = taskList.get(parser.getIndex());
            selectedTask.setDone();
            ui.displayAction("Marked selected task as done", selectedTask);
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "unmark": {

          try {
            // set current task as un-done
            Task selectedTask = taskList.get(parser.getIndex());
            selectedTask.setUnDone();
            ui.displayAction("Marked selected task as un-done desu", selectedTask);
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }

          break;
        }
        case "todo": {
          try {
            Task curentTask = new Todo(parser.getTaskName());
            taskList.add(curentTask);
            System.out.println("added:\t" + ui.displayTask(curentTask));
          } catch (StringIndexOutOfBoundsException ex) {

            System.out.println("Please enter a name after the todo command!");
          }

          break;
        }
        case "deadline": {

          try {
            Task curentTask = parser.parseDeadline();
            taskList.add(curentTask);
            System.out.println("added:\t" + ui.displayTask(curentTask));
          } catch (ParserException ex) {
            System.out.println(ex.getMessage());
          }

          break;
        }
        case "event": {
          try {
            Task curentTask = parser.parseEvent();
            taskList.add(curentTask);
            System.out.println("added:\t" + ui.displayTask(curentTask));
          } catch (ParserException ex) {
            System.out.println(ex.getMessage());
          }
          break;
        }
        case "delete": {
          if (taskList.isEmpty()) {
            System.out.println("The list is empty!");
            break;
          }
          try {
            // remove the current task
            Task selectedTask = taskList.get(parser.getIndex());
            taskList.remove(parser.getIndex());
            ui.displayAction("Deleting selected task!", selectedTask);
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please enter a valid index!");
          }
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
