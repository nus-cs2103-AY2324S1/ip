package rua.common;

import java.io.InputStream;
import java.util.Scanner;


public class Ui {
    private final Scanner in;

    private static final String DIVIDING_LINE = "____________________________________________________________";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public void showLine() {
        StringLogger.append(DIVIDING_LINE);
    }

    public void showWelcome() {
        final String welcomeMessage = " Hello! I'm Rua, your ChatBot\n"
                + " What can I do for you?\n";
        StringLogger.append(DIVIDING_LINE + "\n" + welcomeMessage
                + DIVIDING_LINE);
    }

    public void showGoodbye() {
        final String goodbyeMessage = " Bye. Hope to see you again soon!\n";
        StringLogger.append(goodbyeMessage);
    }

    public void showMessage(String str) {
       StringLogger.append(str);
    }

    public void showError(String errorMessage) {
        final String errorOpeningMessage = "You get an error: ";
        StringLogger.append(errorOpeningMessage + errorMessage + "\n");
    }

    public void showLoadingError() {
        final String loadingErrorMessage = "Given tasklist cannot be loaded. Now creating a new tasklist instead.";
        showError(loadingErrorMessage);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
