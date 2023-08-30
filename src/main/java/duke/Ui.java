package duke;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "\t____________________________________________________________";

    public void showWelcome() {
        String intro_message = "\t____________________________________________________________\n"
                + "\t Hello! I'm Bob the Chatbot!\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";

        System.out.println(intro_message);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showByeMessage() {
        print("Bye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public void showError(String errorMsg) {
        print("☹ OOPS!!! " + errorMsg);
    }

    public void print(String message) {
        System.out.println("\t " + message);
    }
}
