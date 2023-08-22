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
    cheeseStack.add("[ ] " + input);
  }

  private void markItem(int index) {
    String input = cheeseStack.get(index);
    String[] inputArray = new String[2];
    inputArray = input.split("]");
    inputArray[0] = "[X] ";
    input = inputArray[0] + inputArray[1];
    cheeseStack.set(index, input);
  }

  private void commandResponse(String input) {
    String inputSplit[] = input.split(" ");
    switch(inputSplit[0]) {
      case "mark":
        markItem(Integer.parseInt(inputSplit[1]) - 1);
        System.out.println("\t-----------------------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + cheeseStack.get(Integer.parseInt(inputSplit[1]) - 1));
        System.out.println("\t-----------------------------------------");
        break;
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
