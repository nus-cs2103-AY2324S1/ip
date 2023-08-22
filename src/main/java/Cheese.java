import java.util.Scanner;

public class Cheese {
  public void speak() {
    Scanner myObj = new Scanner(System.in);
    String echoedInput = "";
    System.out.println("\t-----------------------------------------");
    System.out.println("\tHello, I'm Cheese");
    System.out.println("\tWhat can I do for you?");
    System.out.println("\t------------------------------------------");
    while (true) {
      echoedInput = myObj.nextLine();
      System.out.println("\t-----------------------------------------");
      System.out.println("\t" + echoedInput);
      System.out.println("\t-----------------------------------------");
      if (echoedInput.equals("bye")) {
        break;
      }
    }
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t-----------------------------------------");
  }

  public static void main(String[] args) {
    Cheese cheese = new Cheese();
    cheese.speak();
  }

}
