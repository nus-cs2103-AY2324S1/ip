import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {

  Parser() {
    inputTokens = null;
    inputString = null;
    scanner = new Scanner(System.in);
  }

  public void update() throws NoSuchElementException {
    inputString = scanner.nextLine();
    inputTokens = inputString.split(" ");
  }


  public String getInputString() {
    return this.inputString;
  }

  public String[] getInputTokens() {
    return this.inputTokens;
  }

  public int getIndex() throws IndexOutOfBoundsException{
    int index = Integer.parseInt(inputTokens[1]);
    index--;
    return index;
  }


  private String inputString;
  private String[] inputTokens;

  private Scanner scanner;
}
