public class UI {

  public UI() {}

  public void printLine() {
    System.out.println("    ____________________________________________________________");
  }

  public void greet() {
    printLine();
    System.out.println("    Hello! I'm Duke");
    System.out.println("    What can I do for you?");
    printLine();
  }

  public void bye() {
    System.out.println("    Bye. Hope to see you again soon!");
    printLine();
  }

}
