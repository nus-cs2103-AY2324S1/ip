package Duke;

import java.util.Scanner;

/**
 * Class to handle interactions with user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Default constructor.
     */
    public Ui(){
        scanner=new Scanner(System.in);
    }

    /**
     * reads user input.
     * @return
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome(){
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Mathan\n" +
                " What can I do for you?");
    }

    /**
     * Prints exit message.
     */
    public void showExit(){
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    /**
     * Prints line spacer.
     */
    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints text to user.
     * @param str
     */
    public void print(String str){
        System.out.println(str);
    }

    /**
     * Prints DukeException errors.
     * @param e
     */
    public void handleError(DukeException e){
        System.out.println(e.getMessage());
    }
}
