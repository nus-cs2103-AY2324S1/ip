import java.util.Scanner;

public class Paimon {
  public final String name = "Paimon";

  public void greetAndFarewell() {
    System.out.println("---------------------------------------------------------------");
    System.out.println(" Hello! I'm  " + name);
    System.out.println(" What can I do for you?");
    System.out.println("---------------------------------------------------------------");

    Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine();

    while (!command.equals("bye")) {
      System.out.println("---------------------------------------------------------------");
      System.out.println(" " + command);
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
