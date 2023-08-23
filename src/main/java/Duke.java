import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
  protected static ArrayList<Task> taskList = new ArrayList<Task>();
  private static final Pattern commandPattern = Pattern.compile("^(\\S+)\\s*");

  enum Command {
    LIST,
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    BYE,
    INVALID
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    greet();
    while (true) {

      String input = scanner.nextLine();

      Command cmd = getCommand(input);

      switch (cmd) {
        case BYE:
          scanner.close();
          exit();
          return;
        case LIST:
          System.out.println(listString());
          break;
        case MARK:
          try {
            handleMark(input);
          } catch (IllegalArgumentException | DukeIOBException e) {
            System.out.println(e.getMessage());
          }
          break;
        case UNMARK:
          try {
            handleUnmark(input);
          } catch (IllegalArgumentException | DukeIOBException e) {
            System.out.println(e.getMessage());
          }
          break;
        case TODO:
          try {
            handleTodo(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case DEADLINE:
          try {
            handleDeadline(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case EVENT:
          try {
            handleEvent(input);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          break;
        case DELETE:
          try {
            handleDelete(input);
          } catch (IllegalArgumentException | DukeIOBException e) {
            System.out.println(e.getMessage());
          }
          break;
        default:
          handleInvalidCommands();
      }
      System.out.println();
    }
  }

  private static Command getCommand(String input) {
    Matcher m = commandPattern.matcher(input);
    String commandString = "";
    if (m.find()) {
      commandString = m.group(1).toUpperCase();
    }
    try {
      return Command.valueOf(commandString);
    } catch (IllegalArgumentException e) {
      return Command.INVALID;
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
      throw new DukeIOBException(idx);
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
      throw new DukeIOBException(idx);
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
      throw new DukeIOBException(idx);
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

  private static void handleInvalidCommands() {
    StringBuilder sb = new StringBuilder();
    for (Command cmd : Command.values()) {
      if (Command.INVALID.equals(cmd)) {
        continue;
      }
      sb.append(cmd.toString().toLowerCase());
      sb.append(", ");
    }
    sb.setLength(sb.length() - 2);
    System.out.printf("Invalid Input%nCommands: %s%n", sb);
  }
}
