package cheese;

import cheese.Ui.Ui;
import cheese.TaskList.TaskList;
import cheese.Storage.Storage;
import cheese.Parser.Parser;
import cheese.Task.Task;

import java.util.Scanner;

/**
 * Represents a Cheesebot that can add, delete, and mark tasks as done.
 *
 * @version v0.1
 */

public class Cheesebot {
  /** The task list of the Cheesebot. */
  private TaskList taskList;
  /** The user interface of the Cheesebot. */
  private Ui ui;
  /** The storage of the Cheesebot. */
  private Storage storage;
  /** The parser of the Cheesebot. */
  private Parser parser = new Parser();

  public Cheesebot() {
    this.taskList = new TaskList();
    this.ui = new Ui();
    this.storage = new Storage("./data/cheese.txt");
  }

  /**
   * Runs the Cheesebot.
   */
  public void run() {
    ui.showWelcome();
    taskList = storage.loadTask();
    Scanner scanner = new Scanner(System.in);
    String userInput;

    do {
      userInput = ui.getUserInput();
      processCommand(userInput);
    } while (!userInput.equals("bye"));

    storage.saveTask(taskList.getCheeseList());
    ui.showBye();
    scanner.close();
  }
  
  /**
   * Processes the user input.
   *
   * @param input The user input.
   */
  private void processCommand(String input) {
    if (parser.isCommand(input)) {
      String command = parser.getCommand(input);
      switch (command) {
        case "bye":
        break;
        case "list":
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
          ui.showMessage((i + 1) + ". " + taskList.getTask(i));
        }
        break;
        case "mark":
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.getSize()) {
          Task task = this.taskList.getTask(taskIndex);
          task.markAsDone();
          ui.showMessage("Nice! I've marked this task as done:\n" + task);
        } else {
          ui.showMessage("Task not found!");
        }
        break;
        case "todo":
        case "deadline":
        case "event":
        Task someTask = parser.parseTask(input);
        if (someTask != null) {
          taskList.addTask(someTask);
          ui.showMessage("Got it. I've added this task:\n  " + taskList.getTask(taskList.getSize() - 1)
            + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        } else {
          ui.showMessage("☹ OOPS!!! Task formatting is weird.");
        }
        break;
        case "delete":
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
          Task removedTask = taskList.getTask(taskNumber);
          taskList.deleteTask(taskNumber);
          ui.showMessage("Noted. I've removed this task:\n  " + removedTask
            + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        } else {
          ui.showMessage("Task not found!");
        }
        break;
        case "find":
        String keyword = input.split(" ")[1];
        TaskList filteredList = taskList.filterTasks(keyword);
        if (filteredList.getSize() > 0) {
          ui.showMessage("Here are the matching tasks in your list:");
          for (int i = 0; i < filteredList.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + filteredList.getTask(i));
          }
        } else {
          ui.showMessage("No matching tasks found!");
        }
        break;
        default:
        ui.showMessage("I'm sorry, but I don't know what that means :-(");
      }
    } else {
      ui.showMessage("I'm sorry, but I don't know what that means :-(");
    }
  }

  public static void main(String[] args) {
    Cheesebot cheesebot = new Cheesebot();
    cheesebot.run();
  }
}

