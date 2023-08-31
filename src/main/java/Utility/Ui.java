package Utility;

import java.util.Scanner;

public class Ui {

  private Scanner scanner;

  public static String line = 
  "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――\n";

  public static String logo = 
  "     ______     _                     ______          __     ____        __ \n"+
  "    / ____/____(_)___  ____ ____     /_  __/__  _  __/ /_   / __ )____  / /_\n"+
  "   / /   / ___/ / __ \\/ __ `/ _ \\     / / / _ \\| |/_/ __/  / __  / __ \\/ __/\n"+
  "  / /___/ /  / / / / / /_/ /  __/    / / /  __/>  </ /_   / /_/ / /_/ / /_  \n"+
  "  \\____/_/  /_/_/ /_/\\__, /\\___/    /_/  \\___/_/|_|\\__/  /_____/\\____/\\__/  \n"+
  "                    /____/                                                  \n";

  public Ui() {
    this.scanner = new Scanner(System.in);
  }

  public void showWelcome() {
    System.out.println(String.format("%sHi, I am a\n%s\nHow can I help you hehe.. (° ͜ʖ °)\n%s", line, logo, line));
  }

  public String readCommand() {
    String input = scanner.nextLine();
    System.out.println(String.format("You said: %s\n%s", input, line));
    return input;
  }

  public void print(String str) {
    System.out.println(str);
  }

  public void showLine() {
    System.out.println(line);
  }

  public void showLoadingError() {
    System.out.println("File failed to load");
  }

  public void showError(String message) {
    System.out.println(message);
  }

  public void showGoodbye() {
    scanner.close();
  }
  
}
