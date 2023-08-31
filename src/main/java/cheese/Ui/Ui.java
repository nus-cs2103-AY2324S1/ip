package cheese.Ui;

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
    System.out.println("\tHello! I'm Cheese");
    System.out.println("\tWhat can I do for you?");
    System.out.println("\tCommands:"+ 
      "\ntodo <task>,"+
      "\ndeadline <task> /by <date>,"+
      "\nevent <task> /at <date>,"+
      "\nlist,"+
      "\ndelete <task number>,"+
      "\nmark <task number>,"+
      //"\nfind <keyword>,"+
      "\nbye");
      
    System.out.println("-----------------------------------------------");
  }
  
  /**
   * show error message
   *
   */
  public void showBye() {
    System.out.println("-----------------------------------------------");
    System.out.println("\tBye. Hope to see you again soon!");
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
