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

  public static void main(String[] args) {
    printText("Hello! I'm Cyrus\nWhat can I do for you?", globalIndentation);
    String command = "";
    Scanner sc = new Scanner(System.in);
    List<String> store = new ArrayList<>();
    while (true) {
      command = sc.nextLine();
      if (command.equals("bye")) break;
      if (command.equals("list")) {
        String output = IntStream
            .range(0, store.size())
            .mapToObj((i) -> String.format("%d. %s", i + 1, store.get(i)))
            .collect(Collectors.joining("\n"));
        printText(output, globalIndentation);
      } else {
        printText(String.format("added: %s", command), globalIndentation);
        store.add(command);
      }
    }
    printText("Bye. Hope to see you again soon!", globalIndentation);
  }
}
