package Forgotten.Ui;

import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    public static final String line = "____________________________________________________________";

    /**
     * This method prints the greeting message.
     */
    public void printGreetMessage() {
        String greet = line + "\n"
                + "Hello! I'm Forgotten\n"
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
    }

    /**
     * This method prints the bye message.
     */
    public void printByeMessage() {
        String bye = line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(bye);
    }

    /**
     * This method reads in user command.
     * @return the command entered by the user, and it is passed to the parser.
     */
    public String readUserCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
