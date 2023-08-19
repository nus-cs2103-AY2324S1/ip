import java.util.Scanner;

public class Duke {
  private final static String delimiter = "-".repeat(60);

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
    printText("Hello! I'm Cyrus\nWhat can I do for you?", 4);
    String command = "";
    Scanner sc = new Scanner(System.in);
    while (true) {
      command = sc.nextLine();
      if (command.equals("bye")) break;
      printText(command, 4);
    }
    printText("Bye. Hope to see you again soon!", 4);
  }
}
