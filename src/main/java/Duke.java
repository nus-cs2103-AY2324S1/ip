import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
  private final static String delimiter = "-".repeat(60);
  private final static int globalIndentation = 4;

  private static void printText(String text, int indentation) {
    String frontPadding = " ".repeat(indentation);
    System.out.printf("%s%s\n", frontPadding, delimiter);
    String[] lines = text.split("\n");
    for (String line : lines) {
      System.out.printf("%s%s\n", frontPadding, line);
    }
    System.out.printf("%s%s\n", frontPadding, delimiter);
  }

  private static int extractIndex(String text, int pos) {
    String[] parts = text.split(" ");
    if (parts.length <= 1) return -1;
    try {
      return Integer.parseInt(parts[pos]);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public static void main(String[] args) {
    printText("Hello! I'm Cyrus\nWhat can I do for you?", globalIndentation);
    String input = "";
    Scanner sc = new Scanner(System.in);
    List<Task> tasks = new ArrayList<>();
    while (true) {
      input = sc.nextLine();
      if (input.equals("bye")) break;
      if (input.startsWith("mark")) {
        int i = extractIndex(input, 1);
        if ((i - 1) < 0 || (i - 1) > tasks.size()) {
          printText("Invalid index provided.", globalIndentation);
        }
        tasks.get(i - 1).markDone();
        printText(
            String.format("Nice! I've marked this task as done:\n%s", tasks.get(i - 1)),
            globalIndentation
        );
      } else if (input.startsWith("unmark")) {
        int i = extractIndex(input, 1);
        if ((i - 1) < 0 || (i - 1) > tasks.size()) {
          printText("Invalid index provided.", globalIndentation);
        }
        tasks.get(i - 1).unmarkDone();
        printText(
            String.format("Nice! I've marked this task as done:\n%s", tasks.get(i - 1)),
            globalIndentation
        );
      } else if (input.equals("list")) {
        String output = IntStream
            .range(0, tasks.size())
            .mapToObj((i) -> String.format("%d. %s", i + 1, tasks.get(i)))
            .collect(Collectors.joining("\n"));
        printText(String.format("Here are the tasks in your list:\n%s", output), globalIndentation);
      } else {
        printText(String.format("added: %s", input), globalIndentation);
        tasks.add(new Task(input));
      }
    }
    printText("Bye. Hope to see you again soon!", globalIndentation);
  }
}
