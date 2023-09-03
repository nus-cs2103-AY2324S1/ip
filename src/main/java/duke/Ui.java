package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints welcome message when chatbot is started.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
    }
//    public boolean blank(String input) {
//        return input.trim().isEmpty();
//    }

    /**
     * Scans user command and returns the command as a string.
     *
     * @return user command a s string.
     */
    public String getUserCommand() {
        Scanner scan = new Scanner(System.in);
        String inData = scan.nextLine();
//        while (blank(inData)) {
//            inData = scan.nextLine();
//        }
        return inData;
    }

    /**
     * Checks if user command is prompting exit.
     *
     * @param input user command as string.
     * @return true is user is prompting exit and false otherwise.
     */
    public boolean isExit(String input) {
        return input.equals("bye");
    }

}
