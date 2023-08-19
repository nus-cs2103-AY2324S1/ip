import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

  static String botName = "GOAT";
  static Scanner input = new Scanner(System.in);
  static List<Task> tasks = new ArrayList<>();

  public static void main(String[] args) {
    String logo =
        "\t   ______    ___        _    _________ \n"
            + "\t .' ___  | .'   `.     / \\  |  _   _  |\n"
            + "\t/ .'   \\_|/  .-.  \\   / _ \\ |_/ | | \\_|\n"
            + "\t| |   ____| |   | |  / ___ \\    | |    \n"
            + "\t\\ `.___]  \\  `-'  /_/ /   \\ \\_ _| |_   \n"
            + "\t `._____.' `.___.'|____| |____|_____|  \n";
    System.out.println("\tHello from\n" + logo);

    greet();
    while (true) { // assumes all commands will be correct (errors are a future task)
      String instruction = input.nextLine().trim();
      String[] commands = instruction.split(" ");
      if (instruction.equals("bye")) break;
      switch (commands[0]) {
        case "list":
          listTasks();
          break;
        case "mark":
          markTask(Integer.parseInt(commands[1]));
          break;
        case "unmark":
          unmarkTask(Integer.parseInt(commands[1]));
          break;
        default:
          addTask(instruction);
      }
    }
    sayBye();
  }

  static void markTask(int i) {
    --i;
    tasks.get(i).mark();

    printLine();
    System.out.println("\t Nice! I've marked this task as done:");
    System.out.printf("\t\t %s\n", tasks.get(i));
    printLine();
  }

  static void unmarkTask(int i) {
    --i;
    tasks.get(i).unmark();

    printLine();
    System.out.println("\t OK, I've marked this task as not done yet");
    System.out.printf("\t\t %s\n", tasks.get(i));
    printLine();
  }

  static void listTasks() {
    printLine();
    System.out.println("\t Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); ++i) System.out.printf("\t %d.%s\n", i + 1, tasks.get(i));
    printLine();
  }

  static void addTask(String name) {
    tasks.add(new Task(name));

    printLine();
    System.out.printf("\t added: %s\n", name);
    printLine();
  }

  static void greet() {
    printLine();
    System.out.printf("\tHello! I'm %s\n", botName);
    System.out.println("\tWhat can I do for you?");
    printLine();
  }

  static void sayBye() {
    printLine();
    System.out.println("\tBye. Hope to see you again soon!");
    printLine();
  }

  static void printLine() {
    String line = "\t____________________________________________________________";
    System.out.println(line);
  }
}
