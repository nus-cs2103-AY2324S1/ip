import java.util.Scanner;

public class Dude {
  // Messages
  static String logo =
    " _|    _| _    O\n" +
      "(_||_|(_|(/_  /İ\\\n";
  static String hello = logo + "\n" +
    "Hello! I'm dude.\n" +
    "What can I do for you?";
  static String goodbye =
    "Bye. Hope to see you again soon!";

  /**
   * Tasks
   */
  public static String[] tasks = new String[100];

  /**
   * Format message/prompt to be printed to console.
   *
   * @param message Message to format. Lines separated by \n.
   * @return Formatted message.
   */
  public static String formatMessage(String message) {
    String[] lines = message.split("\\n");
    String border = "-----------------------------------------------------------\n";
    String prefix = "  ";
    String output = border + prefix +
      String.join("\n" + prefix, lines) + "\n" +
      border;
    return output;
  }

  /**
   * Parse input and act accordingly.
   *
   * @param input Input to parse.
   * @return `true` if a subsequent command can be taken, `false` if not (i.e. quit)
   */
  public static boolean parseInput(String input) {
    // extract command (strip leading and trailing whitespace, take first word)
    String cmd = input.split(" ")[0];

    switch (cmd) {
      case "bye":
        // quit
        return false;
      default:
        // echo input
        System.out.println(formatMessage(input));
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Print startup greeting
    System.out.println(formatMessage(hello));

    // Input loop -- wait for input, respond, repeat
    boolean shouldContinue = true;
    while (shouldContinue) {
      // read user input
      String input = sc.nextLine();
      shouldContinue = parseInput(input);
    }

    // Print shutdown greeting
    System.out.println(formatMessage(goodbye));
  }
}
