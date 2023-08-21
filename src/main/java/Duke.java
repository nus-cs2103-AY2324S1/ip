import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";
    System.out.println(greeting);
    System.out.println(info);

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String inputString = scanner.nextLine();
      if (inputString.equals("bye")) {
        break;
      } else {
        System.out.println("\t" + inputString);
      }
    }

    System.out.println(goodbye);

  }
}
