import java.util.Scanner;

public class VerboseUi extends Ui {

  private static final String HORIZONTAL_LINE = "_".repeat(60);

  public VerboseUi() {
    super(new Scanner(System.in));
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

  public void printIndent(String msg) {
    for (String line : msg.split("\n")) {
      System.out.println(" ".repeat(2) + line);
    }
  }

}
