import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paimon {

  private final String name = "Paimon";
  private List<Task> tasks = new ArrayList<>();

  public void listTasks() {
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < this.tasks.size(); i++) {
      System.out.println(" " + (i + 1) +
              ".[" + this.tasks.get(i).getStatusIcon() +
              "] " + this.tasks.get(i).toString());
    }
  }

  public void readTask(String command, Task t) {
    if (command.equals("list")) {
      this.listTasks();
    } else if (command.startsWith("mark")) {
      System.out.println(" Nice! I've marked this task as done:");
      char c = command.charAt(command.length() - 1);
      int num = c - '0';
      this.tasks.get(num - 1).markAsDone();
      System.out.println("   [X] " + this.tasks.get(num - 1).toString());
    } else if (command.startsWith("unmark")) {
      System.out.println(" OK, I've marked this task as not done yet:");
      char c = command.charAt(command.length() - 1);
      int num = c - '0';
      this.tasks.get(num - 1).markAsNotDone();
      System.out.println("   [ ] " + this.tasks.get(num - 1).toString());
    } else {
      System.out.println(" added: " + command);
      this.tasks.add(t);
    }
  }

  public void greetAndFarewell() {
    System.out.println("---------------------------------------------------------------");
    System.out.println(" Hello! I'm  " + name);
    System.out.println(" What can I do for you?");
    System.out.println("---------------------------------------------------------------");

    Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine();
    Task t = new Task(command);

    while (!command.equals("bye")) {
      System.out.println("---------------------------------------------------------------");
      this.readTask(command, t);
      System.out.println("---------------------------------------------------------------");
      command = scanner.nextLine();
      t = new Task(command);
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
