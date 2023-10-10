package cheese.ui;

import java.util.Scanner;

public class Ui {
  private Scanner sc;

  public Ui() {
    sc = new Scanner(System.in);
  }

  /**
   * get user input
   *
   * @return String of user input
   */
  public String getUserInput() {
    return sc.nextLine();
  }

  /**
   * show welcome message
   */
  public void showWelcome() {
    System.out.println("-----------------------------------------------");
    System.out.println("\tHello! Cheese is Cheese");
    System.out.println("\tWhat can Cheese do for you?");
    System.out.println("\tCommands:"+ 
      "\n\t1. todo <task>,"+
      "\n\t2. deadline <task> /by dd-MM-yyyy,"+
      "\n\t3. event <task> /from <day> /to <day>,"+
      "\n\t4. list,"+
      "\n\t5. delete <task number>,"+
      "\n\t6. mark <task number>,"+
      "\n\t7. find <keyword>,"+
      "\n\t8. bye,"+
      "\n\t9. help");

    System.out.println("-----------------------------------------------");
  }

  /**
   * Return welcome message as string
   * @return String of welcome message
   */
  public String welcomeMessage() {

    String firstLine = "\tHello! Cheese is Cheese\n";
    String secondLine = "\tWhat can Cheese do for you?\n";
    String thirdLine = "\tCommands:"+ 
      "\n\t1. todo <task>,"+
      "\n\t2. deadline <task> /by dd-MM-yyyy,"+
      "\n\t3. event <task> /from <day> /to <day>,"+
      "\n\t4. list,"+
      "\n\t5. delete <task number>,"+
      "\n\t6. mark <task number>,"+
      "\n\t7. find <keyword>,"+
      "\n\t8. bye,"+
      "\n\t9. help\n";

    return firstLine + secondLine + thirdLine;
  }

  /**
   * show Bye message
   *
   */
  public void showBye() {
    System.out.println("-----------------------------------------------");
    System.out.println("\tBye. Cheese hope to see you again soon!");
    System.out.println("-----------------------------------------------");
  }

  /**
   * show input message
   *
   * @param message input message
   */
  public void showMessage(String message) {
    System.out.println("-----------------------------------------------");
    System.out.println("\t" + message);
    System.out.println("-----------------------------------------------");
  }

  public void closeScanner() {
    sc.close();
  }

} 
