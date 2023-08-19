import java.util.ArrayList;
import java.util.Scanner;

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
        + "'list' to list\n"
        + "'mark <?>'\n"
        + "'unmark <?>'\n"
        + "'bye' to exit\n";
    System.out.println(introduction);

    while (true) {
      System.out.print(">");
      String input = scanner.nextLine();

      String splitInput[] = input.split(" ");

      if (splitInput[0].compareToIgnoreCase("bye") == 0) {
        break;
      }

      if (splitInput[0].compareToIgnoreCase("list") == 0) {
        for (int i = 0; i < tasks.size(); i++) {
          System.out.println((i + 1) + ". " + tasks.get(i));
        }
        continue;
      }

      if (splitInput[0].compareToIgnoreCase("mark") == 0) {
        Task selectedTask = tasks.get(Integer.parseInt(splitInput[1]) - 1);
        selectedTask.markDone();
        System.out.println("Task marked as done");
        System.out.println(selectedTask);
        continue;
      }

      if (splitInput[0].compareToIgnoreCase("unmark") == 0) {
        Task selectedTask = tasks.get(Integer.parseInt(splitInput[1]) - 1);
        selectedTask.markNotDone();
        System.out.println("Task marked as not done");
        System.out.println(selectedTask);
        continue;
      }

      tasks.add(new Task(input));
      System.out.println("Added: " + input);
    }

    System.out.println("Bye. Hope to see you again soon!");
    scanner.close();
  }
}
