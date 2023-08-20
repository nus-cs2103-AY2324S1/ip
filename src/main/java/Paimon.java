import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paimon {

  private final String name = "Paimon";
  private List<Task> tasks = new ArrayList<>();

  public void listTasks() {
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < this.tasks.size(); i++) {
      System.out.println(" " + (i + 1) + "." + this.tasks.get(i).toString());
    }
  }

  public void readTask(String command) {
    if (command.equals("list")) {
      this.listTasks();
    } else if (command.startsWith("mark")) {
      System.out.println(" Nice! I've marked this task as done:");
      char c = command.charAt(command.length() - 1);
      int num = c - '0';
      this.tasks.get(num - 1).markAsDone();
      System.out.println("   " + this.tasks.get(num - 1).toString());
    } else if (command.startsWith("unmark")) {
      System.out.println(" OK, I've marked this task as not done yet:");
      char c = command.charAt(command.length() - 1);
      int num = c - '0';
      this.tasks.get(num - 1).markAsNotDone();
      System.out.println("   " + this.tasks.get(num - 1).toString());
    } else {
      this.addTask(command);
    }
  }

  public void addTask(String command) {
    System.out.println(" Got it. I've added this task: ");
    if (command.startsWith("todo")) {
      Task t = new Todo(command.substring("todo ".length()));
      this.tasks.add(t);
      System.out.println("   " + t.toString());
    } else if (command.startsWith("deadline")) {
      int byIndex = command.indexOf("/by");
      String taskDescription = command.substring("deadline ".length(), byIndex).trim();
      String deadline = command.substring(byIndex + "/by ".length());
      Task t = new Deadline(taskDescription, deadline);
      this.tasks.add(t);
      System.out.println("   " + t.toString());
    } else if (command.startsWith("event")) {
      int fromIndex = command.indexOf("/from");
      int toIndex = command.indexOf("/to");
      String taskDescription = command.substring("event ".length(), fromIndex).trim();
      String from = command.substring(fromIndex + "/from ".length(), toIndex).trim();
      String to = command.substring(toIndex + "/to ".length());
      Task t = new Event(taskDescription, from, to);
      this.tasks.add(t);
      System.out.println("   " + t.toString());
    }
    System.out.println(" Now you have " + this.tasks.size() + " tasks in the list");
  }

  public void greetAndFarewell() {
    System.out.println("---------------------------------------------------------------");
    System.out.println(" Hello! I'm  " + name);
    System.out.println(" What can I do for you?");
    System.out.println("---------------------------------------------------------------");

    Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine();

    while (!command.equals("bye")) {
      System.out.println("---------------------------------------------------------------");
      this.readTask(command);
      System.out.println("---------------------------------------------------------------");
      command = scanner.nextLine();
    }

    scanner.close();
    System.out.println("---------------------------------------------------------------");
    System.out.println(" Bye. Hope to see you again soon!");
    System.out.println("---------------------------------------------------------------");
  }

  public static void main(String[] args) {
    Paimon chatBot = new Paimon();
    chatBot.greetAndFarewell();
  }
}
