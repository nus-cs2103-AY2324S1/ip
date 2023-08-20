public class Paimon {
  public final String name = "Paimon";

  public void greetAndFarewell() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("Hello! I'm  " + name);
    System.out.println("What can I do for you?");
    System.out.println("---------------------------------------------------------------");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("---------------------------------------------------------------");
  }

  public static void main(String[] args) {
    Paimon chatBot = new Paimon();
    chatBot.greetAndFarewell();
  }
}
