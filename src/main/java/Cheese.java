

public class Cheese {
  public void speak() {
    System.out.println("-----------------------------------------");
    System.out.println("Hello, I'm Cheese");
    System.out.println("What can I do for you?");
    System.out.println("------------------------------------------");
    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("-----------------------------------------");
  }

  public static void main(String[] args) {
    Cheese cheese = new Cheese();
    cheese.speak();
  }

}
