import java.util.Scanner;

public class VerboseUi extends Ui {

  private static final String HORIZONTAL_LINE = "_".repeat(60);

  private String name;

  public VerboseUi(String name) {
    super(new Scanner(System.in));
    this.name = name;
  }

  @Override
  public void greet() {
    print(String.format("Hello! I'm %s\nWhat can I do for you?", name));
  }

  @Override
  public void print(String msg) {
    printIndent(HORIZONTAL_LINE);
    for (String line : msg.split("\n")) {
      printIndent(" ".repeat(2) + line);
    }
    printIndent(HORIZONTAL_LINE);
    System.out.println();
  }

  private void printIndent(String msg) {
    for (String line : msg.split("\n")) {
      System.out.println(" ".repeat(2) + line);
    }
  }

}
