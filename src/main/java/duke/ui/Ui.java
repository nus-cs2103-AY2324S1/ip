package duke.ui;

import java.util.Scanner;

public class Ui {
    private String ChatBotName = "Carl";
    Scanner SC;

    public Ui() {
        this.SC = new Scanner(System.in);
    }
    private String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    public void sendMessage(String message) {
        System.out.println(messageCard(message));
    }

    public void showLoadingError() {
        System.out.println((messageCard("Carl: Error Loading File")));
    }

    public void showError(String message) {
        System.out.println((messageCard(message)));
    }

    public void showWelcome() {
        System.out.println(messageCard("Hello! I'm " + ChatBotName
                + "\n\t What can I do for you?"));
    }

    public String readCommand() {
        String userInput = this.SC.nextLine();
        return userInput;
    }
}
