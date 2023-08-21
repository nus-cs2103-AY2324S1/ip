import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  public static void main(String[] args) {

    String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
    String info = "Ask the bunny a question!\n";
    String goodbye = "Bye. See you again! (またね)";

    ArrayList<String> userInputs = new ArrayList<>();
    System.out.println(greeting);
    System.out.println(info);

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String inputString = scanner.nextLine();
      if (inputString.equals("bye")) {
        break;
      } else if (inputString.equals("list")) {
        for (int i = 0; i < userInputs.size(); i++) {
          String index = Integer.toString(i + 1);
          System.out.println(index + "\t" + userInputs.get(i));
        }
      } else {
        userInputs.add(inputString);
        System.out.println("added:\t" + inputString);
      }
    }

    System.out.println(goodbye);

  }
}
