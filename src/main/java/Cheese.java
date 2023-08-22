import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Cheese {
  private List<String> cheeseStack = new ArrayList<String>();

  public void speak() {
    Scanner myObj = new Scanner(System.in);
    String echoedInput = "";
    System.out.println("\t-----------------------------------------");
    System.out.println("\tHello, I'm Cheese");
    System.out.println("\tWhat can I do for you?");
    System.out.println("\t------------------------------------------");
    while (true) {
      echoedInput = myObj.nextLine();
      commandResponse(echoedInput);
      if (echoedInput.equals("bye")) {
        break;
      }
    }
    System.out.println("\t-----------------------------------------");
  }

  private void addToList(String input) {
    cheeseStack.add(input);
  }

  private void commandResponse(String input) {
    switch(input) {
      case "bye":
        System.out.println("\tBye. Hope to see you again soon!");
        break;
      case "list":
        System.out.println("\t-----------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < cheeseStack.size(); i++) {
          System.out.println("\t" + (i + 1) + ". " + cheeseStack.get(i));
        }
        System.out.println("\t-----------------------------------------");
        break;
      default:
        addToList(input);
        System.out.println("\t-----------------------------------------");
        System.out.println("\tadded: " + input);
        System.out.println("\t-----------------------------------------");
        break;

    }
  }

  public static void main(String[] args) {
    Cheese cheese = new Cheese();
    cheese.speak();
  }

}
