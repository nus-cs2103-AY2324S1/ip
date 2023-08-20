import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paimon {

  private final String name = "Paimon";

  private List<String> tasks = new ArrayList<>();

  public void listTasks() {
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println(" " + (i + 1) + ". " + tasks.get(i));
    }
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
      if (!command.equals("list")) {
        System.out.println(" added: " + command);
        this.tasks.add(command);
      } else {
        this.listTasks();
      }
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
