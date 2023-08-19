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

    while (true) {
      System.out.print(">");
      String input = scanner.nextLine();
      Matcher m;

      try {
        m = commandParser(input);
      } catch (HoroException e) {
        System.out.println(e.getMessage());
        System.out.println("Please use a valid command!");
        continue;
      }

      String command = m.group(1);

      if (command.equals("bye")) {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
        System.exit(0);
      }

      if (command.equals("list")) {
        System.out.println("-----Tasks-----");
        for (int i = 0; i < tasks.size(); i++) {
          System.out.println((i + 1) + ". " + tasks.get(i));
        }
        continue;
      }

      if (command.equals("mark") || command.equals("unmark")) {
        if (tasks.isEmpty()) {
          System.out.println("No tasks available");
          continue;
        }
        Task selectedTask = null;
        try {
          selectedTask = tasks.get(Integer.parseInt(m.group(2)) - 1);
        } catch (Exception e) {
          System.out.println("Please enter a valid number from 1 - " + tasks.size());
          continue;
        }

        if (command.equals("mark")) {
          selectedTask.markDone();
          System.out.println("Task marked as done");
        } else {
          selectedTask.markNotDone();
          System.out.println("Task marked as not done");
        }
        System.out.println(selectedTask);
        continue;
      }

      if (command.equals("delete")) {
        if (tasks.isEmpty()) {
          System.out.println("No tasks available");
          continue;
        }
        Task removedTask = null;
        try {
          removedTask = tasks.remove(Integer.parseInt(m.group(2)) - 1);
        } catch (Exception e) {
          System.out.println("Please enter a valid number from 1 - " + tasks.size());
          continue;
        }
        System.out.println("Removed task: ");
        System.out.println(removedTask);
        continue;
      }

      if (command.equals("todo")) {
        Todo newTodo;
        try {
          newTodo = new Todo(m.group(2));
        } catch (HoroException e) {
          System.out.println(e.getMessage());
          continue;
        }
        tasks.add(newTodo);
        System.out.println("Added: ");
        System.out.println(newTodo);
        continue;
      }

      if (command.equals("deadline")) {
        Matcher deadlineMatcher = Pattern
            .compile("(\\/by) ([\\w ]+)")
            .matcher(input);
        try {
          if (!deadlineMatcher.find()) {
            throw new HoroException("Wrong command format");
          }
        } catch (HoroException e) {
          System.out.println(e.getMessage());
          System.out.println("deadline <description> /by <time>");
          continue;
        }
        Deadline newDeadline;
        try {
          newDeadline = new Deadline(m.group(2), deadlineMatcher.group(2));
        } catch (HoroException e) {
          System.out.println(e.getMessage());
          continue;
        }
        tasks.add(newDeadline);
        System.out.println("Added: ");
        System.out.println(newDeadline);
        continue;
      }

      if (command.equals("event")) {
        Matcher eventMatcher = Pattern
            .compile("(\\/from) ([\\w ]+) (\\/to) ([\\w ]+)")
            .matcher(input);
        try {
          if (!eventMatcher.find()) {
            throw new HoroException("Wrong command format");
          }
        } catch (HoroException e) {
          System.out.println(e.getMessage());
          System.out.println("event <description> /from <time> /to <time>");
          continue;
        }
        Event newEvent;
        try {
          newEvent = new Event(m.group(2), eventMatcher.group(2), eventMatcher.group(4));
        } catch (HoroException e) {
          System.out.println(e.getMessage());
          continue;
        }
        tasks.add(newEvent);
        System.out.println("Added: ");
        System.out.println(newEvent);
        continue;
      }
    }
  }

  private static Pattern commandPattern = Pattern
      .compile("^(deadline|todo|event|bye|mark|unmark|list|delete) *([\\w ]+)*");

  private static Matcher commandParser(String command) throws HoroException {
    Matcher m = commandPattern.matcher(command);
    if (!m.find()) {
      throw new HoroException("Invalid Command");
    }
    return m;
  }
}
