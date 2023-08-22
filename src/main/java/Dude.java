public class Dude {
  static String logo =
    " _|    _| _    O\n" +
      "(_||_|(_|(/_  /'\\\n";

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
    String hello = logo + "\n" +
      "Hello! I'm dude.\n" +
      "What can I do for you?";
    String goodbye =
      "Bye. Hope to see you again soon!";
    printMessage(hello);
    printMessage(goodbye);
  }
}
