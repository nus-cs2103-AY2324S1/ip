import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
  protected static ArrayList<Task> taskList = new ArrayList<Task>();
  private static final Pattern commandPattern = Pattern.compile("^(\\S+)\\s*");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    greet();
    while (true) {

      String input = scanner.nextLine();

      Matcher m = commandPattern.matcher(input);
      String cmd = "";
      if (m.find()) {
        cmd = m.group(1);
      }

      switch (cmd) {
        case "bye":
          scanner.close();
          exit();
          return;
        case "list":
          System.out.println(listString());
          break;
        case "mark":
          try {
            handleMark(input);
          } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "unmark":
          try {
            handleUnmark(input);
          } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "todo":
          try {
            handleTodo(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "deadline":
          try {
            handleDeadline(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "event":
          try {
            handleEvent(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "delete":
          try {
            handleDelete(input);
          } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
          }
          break;
        default:
          handleInvalid();
      }
      System.out.println();
    }
  }

  private static void handleMark(String input) {
    Pattern validPattern = Pattern.compile("^mark\\s+(\\d+)$");
    if (!validPattern.matcher(input).find()) {
      throw new IllegalArgumentException(
          "Invalid arguments for mark\nPlease follow: mark <task_num>");
    }
    int idx = parseIndex(input);
    if (idx < 0 || idx > taskList.size()) {
      throw new IndexOutOfBoundsException("Task " + idx + " does not exist");
    } else {
      taskList.get(idx - 1).markAsDone();
      System.out.printf("Nice! I've marked this task as done:%n %s%n", taskList.get(idx - 1));
    }
  }

  private static void handleUnmark(String input) {
    Pattern validPattern = Pattern.compile("^unmark\\s+(\\d+)$");
    if (!validPattern.matcher(input).find()) {
      throw new IllegalArgumentException(
          "Invalid arguments for unmark\nPlease follow: unmark <task_num>");
    }
    int idx = parseIndex(input);
    if (idx <= 0 || idx > taskList.size()) {
      throw new IndexOutOfBoundsException("Task " + idx + " does not exist");
    } else {
      taskList.get(idx - 1).markAsNotDone();
      System.out.printf("OK! I've marked this task as not done:%n %s%n", taskList.get(idx - 1));
    }
  }

  private static void handleDelete(String input) {
    Pattern validPattern = Pattern.compile("^delete\\s+(\\d+)$");
    if (!validPattern.matcher(input).find()) {
      throw new IllegalArgumentException(
          "Invalid arguments for delete\nPlease follow: delete <task_num>");
    }
    int idx = parseIndex(input);
    if (idx <= 0 || idx > taskList.size()) {
      throw new IndexOutOfBoundsException("Task " + idx + " does not exist");
    } else {
      Task deletedTask = taskList.get(idx - 1);
      taskList.remove(idx - 1);
      System.out.printf(
          "Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.%n",
          deletedTask, taskList.size());
    }
  }

  private static void handleTodo(String input) {
    Pattern p = Pattern.compile("^todo\\s+(.+)");
    Matcher m = p.matcher(input);
    if (m.find()) {
      TodoTask newTask = new TodoTask(m.group(1));
      addTask(newTask);
    } else {
      throw new IllegalArgumentException("Invalid arguments for todo\nPlease follow: todo <task>");
    }
  }

  private static void handleDeadline(String input) {
    Pattern p = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)");
    Matcher m = p.matcher(input);
    if (m.find()) {
      DeadlineTask newTask = new DeadlineTask(m.group(1), m.group(2));
      addTask(newTask);
    } else {
      throw new IllegalArgumentException(
          "Invalid arguments for deadline\nPlease follow: deadline <task> /by <deadline_date>");
    }
  }

  private static void handleEvent(String input) {
    Pattern p = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)");
    Matcher m = p.matcher(input);
    if (m.find()) {
      EventTask newTask = new EventTask(m.group(1), m.group(2), m.group(3));
      addTask(newTask);
    } else {
      throw new IllegalArgumentException(
          "Invalid arguments for event\nPlease follow: event <task> /from <from_date> /to <to_date>");
    }
  }

  private static void greet() {
    System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
  }

  private static void exit() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  private static int parseIndex(String input) {
    Pattern indexPattern = Pattern.compile("(\\d+)$");
    Matcher m = indexPattern.matcher(input);
    int res = -1;
    if (m.find()) {
      String idx = m.group(1);
      res = Integer.parseInt(idx);
    }
    return res;
  }

  private static void addTask(Task newTask) {
    taskList.add(newTask);
    System.out.printf(
        "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.%n",
        newTask, taskList.size());
  }

  private static String listString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Here are your tasks:\n");
    for (int i = 0; i < taskList.size(); i++) {
      sb.append(i + 1);
      sb.append(".");
      sb.append(taskList.get(i).toString());
      sb.append("\n");
    }
    sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  private static void handleInvalid() {
    System.out.println(
        "Invalid Input\nCommands: list, todo, deadline, event, mark, unmark, delete, bye");
  }
}
