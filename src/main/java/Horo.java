import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Horo {
  private static ArrayList<Task> tasks = new ArrayList<Task>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    loadTasks();
    welcomeMessage();

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
          System.out.println("-----Tasks-----");
          for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
          }
          break;
        case MARK:
        case UNMARK:
          if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            break;
          }

          Task selectedTask = null;
          try {
            selectedTask = tasks.get(Integer.parseInt(m.group(1)) - 1);
          } catch (Exception e) {
            System.out.println("Please enter a valid number from 1 - " + tasks.size());
            break;
          }

          if (command.equals(Command.MARK)) {
            selectedTask.markDone();
            System.out.println("Task marked as done");
          } else {
            selectedTask.markNotDone();
            System.out.println("Task marked as not done");
          }
          System.out.println(selectedTask);
          break;
        case DELETE:
          if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            break;
          }
          try {
            Task removedTask = tasks.remove(Integer.parseInt(m.group(1)) - 1);
            System.out.println("Removed task: ");
            System.out.println(removedTask);
          } catch (Exception e) {
            System.out.println("Please enter a valid number from 1 - " + tasks.size());
            break;
          }
          break;
        case TODO:
          try {
            addTask(new Todo(m.group(1)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          break;
        case DEADLINE:
          try {
            addTask(new Deadline(m.group(1), m.group(2)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          break;
        case EVENT:
          try {
            addTask(new Event(m.group(1), m.group(2), m.group(3)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          break;
        default:
          break;
      }
    }
  }

  private static void welcomeMessage() {
    String logo = " _    _ \n"
        + "| |  | |\n"
        + "| |__| | ___  _ __ ___\n"
        + "|  __  |/ _ \\| '__/ _ \\\n"
        + "| |  | | (_) | | | (_) |\n"
        + "|_|  |_|\\___/|_|  \\___/\n";
    System.out.println(logo);

    String introduction = "Hello! I'm Horo\n"
        + "What can I do for you?\n"
        + "Usage: \n"
        + " todo <description>\n"
        + " deadline <description> /by <time>\n"
        + " event <description> /from <time> /to <time>\n"
        + " list\n"
        + " mark <number>\n"
        + " unmark <number>\n"
        + " delete <number>\n"
        + " bye\n";
    System.out.println(introduction);
  }

  private static void exit() {
    System.out.println("Bye. Hope to see you again soon!");
    scanner.close();
    System.exit(0);
  }

  private static void loadTasks() {
    try {
      File taskFile = new File("./data/tasks.txt");
      if (taskFile.createNewFile()) {
        System.out.println("File created: " + taskFile.getName());
      }

      Scanner scanner = new Scanner(taskFile);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        parseTaskString(data);
        // System.out.println(data);
      }
      scanner.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (HoroException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void parseTaskString(String s) throws HoroException {
    String[] arguments = s.split(",");
    Task t;
    switch (arguments[0]) {
      case "T":
        t = new Todo(arguments[2]);
        break;
      case "D":
        t = new Deadline(arguments[2], arguments[3]);
        break;
      case "E":
        t = new Event(arguments[2], arguments[3], arguments[4]);
        break;
      default:
        System.out.println("Bad Command");
        return;
    }
    if (arguments[1].equals("1")) {
      t.markDone();
    }
    tasks.add(t);
  }

  private static void addTask(Task newTask) {
    tasks.add(newTask);
    System.out.println("Added: ");
    System.out.println(newTask);
  }
}