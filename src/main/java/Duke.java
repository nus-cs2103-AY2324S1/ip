import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
  protected static ArrayList<Task> taskList = new ArrayList<Task>();
  private static final Pattern markPattern = Pattern.compile("^mark\\s(\\d+)$");
  private static final Pattern unmarkPattern = Pattern.compile("^unmark\\s(\\d+)$");
  private static final Pattern indexPattern = Pattern.compile("(\\d+)$");
  private static final Pattern todoPattern = Pattern.compile("^todo\\s(.+)");
  private static final Pattern deadlinePattern = Pattern.compile("^deadline\\s(.+)\\s/by\\s(.+)");
  private static final Pattern eventPattern =
      Pattern.compile("^event\\s(.+)\\s/from\\s(.+)/to\\s(.+)");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    greet();
    while (true) {
      String input = scanner.nextLine();

      if (input.equals("bye")) {
        scanner.close();
        exit();
        return;
      } else if (input.equals("list")) {
        System.out.println(listString());
      } else if (markPattern.matcher(input).find()) {
        int idx = parseMark(input);
        if (idx != 0) {
          taskList.get(idx - 1).markAsDone();
          System.out.printf("Nice! I've marked this task as done:%n %s%n%n", taskList.get(idx - 1));
        }
      } else if (unmarkPattern.matcher(input).find()) {
        int idx = parseMark(input);
        if (idx != 0) {
          taskList.get(idx - 1).markAsNotDone();
          System.out.printf(
              "OK! I've marked this task as not done:%n %s%n%n", taskList.get(idx - 1));
        }
      } else if (todoPattern.matcher(input).find()) {
        Matcher m = todoPattern.matcher(input);
        if (m.find()) {
          TodoTask newTask = new TodoTask(m.group(1));
          addTask(newTask);
        }
      } else if (deadlinePattern.matcher(input).find()) {
        Matcher m = deadlinePattern.matcher(input);
        if (m.find()) {
          DeadlineTask newTask = new DeadlineTask(m.group(1), m.group(2));
          addTask(newTask);
        }
      } else if (eventPattern.matcher(input).find()) {
        Matcher m = eventPattern.matcher(input);
        if (m.find()) {
          EventTask newTask = new EventTask(m.group(1), m.group(2), m.group(3));
          addTask(newTask);
        }
      } else {
        System.out.println("Invalid Input\n");
      }
    }
  }

  private static void greet() {
    System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
  }

  private static void exit() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  private static int parseMark(String input) {
    Matcher m = indexPattern.matcher(input);
    int res = 0;
    if (m.find()) {
      String idx = m.group(1);
      res = Integer.parseInt(idx);
    }
    if (res > taskList.size()) {
      System.out.println("Task " + res + " does not exist\n");
      res = 0;
    }
    return res;
  }

  private static void addTask(Task newTask) {
    taskList.add(newTask);
    System.out.printf(
            "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.%n%n",
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
    return sb.toString();
  }
}
