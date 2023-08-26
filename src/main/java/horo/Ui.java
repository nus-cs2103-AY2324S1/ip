package horo;

public class Ui {

  public Ui() {
  }

  public void showWelcome() {
    String logo = " _    _ \n"
        + "| |  | |\n"
        + "| |__| | ___  _ __ ___\n"
        + "|  __  |/ _ \\| '__/ _ \\\n"
        + "| |  | | (_) | | | (_) |\n"
        + "|_|  |_|\\___/|_|  \\___/\n";
    System.out.println(logo);

    String introduction = "Hello! I'm Horo\n"
        + "What can I do for you?\n"
        + "Usage: \n"
        + " todo <description>\n"
        + " deadline <description> /by <time>\n"
        + " event <description> /from <time> /to <time>\n"
        + " list\n"
        + " mark <number>\n"
        + " unmark <number>\n"
        + " delete <number>\n"
        + " bye\n";
    System.out.println(introduction);
  }
}
