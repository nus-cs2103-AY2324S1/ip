import java.util.Scanner;

public class Dude {
  static String logo =
    " _|    _| _    O\n" +
      "(_||_|(_|(/_  /İ\\\n";

  /**
   * Print message/prompt to console.
   *
   * @param message Message to print. Lines separated by \n.
   */
  private static void printMessage(String message) {
    String[] lines = message.split("\\n");
    String border = "-----------------------------------------------------------\n";
    String prefix = "  ";
    String output = border + prefix +
      String.join("\n" + prefix, lines) + "\n" +
      border;
    System.out.println(output);
  }

  public static void main(String[] args) {
    /* Messages */
    String hello = logo + "\n" +
      "Hello! I'm dude.\n" +
      "What can I do for you?";
    String goodbye =
      "Bye. Hope to see you again soon!";

    Scanner sc = new Scanner(System.in);

    // Print startup greeting
    printMessage(hello);

    // Input loop -- wait for input, respond, repeat
    while (true) {
      // read user input
      String input = sc.nextLine();
      // interpret command (strip leading and trailing whitespace, take first word)
      String cmd = input.split(" ")[0];

      // check for quit cmd
      if (cmd.equals("bye")) {
        break;
      }
      // echo input
      printMessage(input);
    }
    // Print shutdown greeting
    printMessage(goodbye);
  }
}
