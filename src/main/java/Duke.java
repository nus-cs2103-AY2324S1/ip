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
      String[] inputTokens = inputString.split(" ");

      // there is no input
      if (inputTokens.length == 0) {
        break;
      }

      String commandString = inputTokens[0];

      if (commandString.equals("bye")) {
        break;
      } else if (commandString.equals("list")) {
        for (int i = 0; i < userInputs.size(); i++) {
          String index = Integer.toString(i + 1);
          System.out.println(index + "\t" + userInputs.get(i));
        }
      } else if (commandString.equals("mark")) {
        int index = Integer.parseInt(inputTokens[1]);
        System.out.println(index);

      } else {
        userInputs.add(inputString);
        System.out.println("added:\t" + inputString);
      }
    }

    System.out.println(goodbye);

  }
}
