package horo;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

import horo.data.Deadline;
import horo.data.Event;
import horo.data.TaskList;
import horo.data.Todo;

public class Horo {

  private Storage storage;
  private TaskList taskList;
  private Ui ui;
  private static Scanner scanner = new Scanner(System.in);

  public Horo(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    taskList = storage.load();
  }

  public void run() {
    ui.showWelcome();

    while (true) {
      System.out.print(">");
      String input = scanner.nextLine();
      Matcher m;
      Command command;
      try {
        command = Command.commandParser(input);
        m = command.getMatcher(input);
      } catch (HoroException e) {
        System.out.println(e.getMessage());
        continue;
      }

      switch (command) {
        case BYE:
          exit();
          break;
        case LIST:
          taskList.showTasks();
          break;
        case MARK:
          taskList.markTaskDone(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case UNMARK:
          taskList.markTaskDone(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case DELETE:
          taskList.removeTask(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case TODO:
          try {
            taskList.addTask(new Todo(m.group(1)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case DEADLINE:
          try {
            taskList.addTask(new Deadline(m.group(1), m.group(2)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case EVENT:
          try {
            taskList.addTask(new Event(m.group(1), m.group(2), m.group(3)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case FIND:
          taskList.findTask(Arrays.asList(m.group(1).split(" ")));
          break;
        default:
          break;
      }
    }
  }

  public static void main(String[] args) {
    new Horo("./data/tasks.txt").run();
  }

  private static void exit() {
    System.out.println("Bye. Hope to see you again soon!");
    scanner.close();
    System.exit(0);
  }

}