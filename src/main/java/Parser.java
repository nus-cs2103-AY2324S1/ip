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

  public int getIndex() throws IndexOutOfBoundsException {
    int index = Integer.parseInt(inputTokens[1]);
    index--;
    return index;
  }

  public String getCommandString() {
    if (inputTokens.length == 0) {
      return "";
    } else {
      return inputTokens[0];
    }
  }

  public String getTaskName() {
    String commandString = this.getCommandString();
    int commandLength = commandString.length() + 1;
    return inputString.substring(commandLength);

  }

  public boolean isInputThere() {
    return inputTokens.length == 0;
  }


  private String inputString;
  private String[] inputTokens;


  private Scanner scanner;
}
