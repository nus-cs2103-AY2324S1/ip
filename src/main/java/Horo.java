import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Horo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    ArrayList<Task> tasks = new ArrayList<Task>();

    String logo = " _    _ \n"
        + "| |  | |\n"
        + "| |__| | ___  _ __ ___\n"
        + "|  __  |/ _ \\| '__/ _ \\\n"
        + "| |  | | (_) | | | (_) |\n"
        + "|_|  |_|\\___/|_|  \\___/\n";
    System.out.println(logo);

    String introduction = "Hello! I'm Horo\n"
        + "What can I do for you?\n"
        + "Type anything to store.\n"
        + "'todo'\n"
        + "'deadline'\n"
        + "'event'\n"
        + "'list' to list\n"
        + "'mark <?>'\n"
        + "'unmark <?>'\n"
        + "'bye' to exit\n";
    System.out.println(introduction);

    Pattern commandPattern = Pattern.compile("^(deadline|todo|event|bye|mark|unmark|list) *([\\w ]+)*");
    while (true) {
      System.out.print(">");
      String input = scanner.nextLine();

      Matcher m = commandPattern.matcher(input);
      m.find();
      String command = m.group(1);

      Task selectedTask = null;

      switch (command) {
        case "bye":
          System.out.println("Bye. Hope to see you again soon!");
          scanner.close();
          System.exit(0);
          break;
        case "list":
          System.out.println("-----Tasks-----");
          for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
          }
          break;
        case "mark":
          selectedTask = tasks.get(Integer.parseInt(m.group(2)) - 1);
          selectedTask.markDone();
          System.out.println("Task marked as done");
          System.out.println(selectedTask);
          break;
        case "unmark":
          selectedTask = tasks.get(Integer.parseInt(m.group(2)) - 1);
          selectedTask.markNotDone();
          System.out.println("Task marked as not done");
          System.out.println(selectedTask);
          break;
        case "todo":
          Todo newTodo = new Todo(m.group(2));
          tasks.add(newTodo);
          System.out.println("Added: ");
          System.out.println(newTodo);
          break;
        case "deadline":
          Matcher deadlineMatcher = Pattern
              .compile("(\\/by) ([\\w ]+)")
              .matcher(input);
          deadlineMatcher.find();
          Deadline newDeadline = new Deadline(m.group(2), deadlineMatcher.group(2));
          tasks.add(newDeadline);
          System.out.println("Added: ");
          System.out.println(newDeadline);
          break;
        case "event":
          Matcher eventMatcher = Pattern
              .compile("(\\/from) ([\\w ]+) (\\/to) ([\\w ]+)")
              .matcher(input);
          eventMatcher.find();
          Event newEvent = new Event(m.group(2), eventMatcher.group(2), eventMatcher.group(4));
          tasks.add(newEvent);
          System.out.println("Added: ");
          System.out.println(newEvent);
          break;
        default:
          tasks.add(new Task(input));
          System.out.println("Added: " + input);
      }
    }
  }
}
