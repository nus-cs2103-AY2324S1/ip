package trackerbot.utils;

import java.util.Scanner;

public class Ui {
    /** Line separators for the console between paragraphs. **/
    private static final String FORMAT_LINE =
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private static final Scanner USER_INPUT = new Scanner(System.in);

    /** Name of the app. **/
    private final String appName;

    private Ui(String appName) {
        this.appName = appName;
    }

    public static Ui instantiate(String appName) {
        Ui ui = new Ui(appName);
        System.out.println(FORMAT_LINE);
        System.out.println("Greetings from " + ui.appName + "!");
        System.out.println("How may I assist?");
        System.out.println(FORMAT_LINE);
        return ui;
    }

    public String readCommand() {
        String input;
        System.out.print("Format :: [keyword] [parse string] | ");
        input = USER_INPUT.nextLine();
        return input;
    }

    public void showLine() {
        System.out.println(FORMAT_LINE);
    }

    public void showError(String message) {
        System.out.println("I got some trouble with that input...\n  " + message);
    }

    public void exitApp() {
        System.out.println("Thank you for using " + appName + ". Goodbye.");
    }

    // generic message method
    public void showMessage(String message) {
        System.out.println(message);
    }
}
