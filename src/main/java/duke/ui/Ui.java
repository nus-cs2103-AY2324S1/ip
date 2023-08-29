package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    private String addLine(String message) {
        String horizontal = "_____________________________________________________\n";
        return horizontal + message + "\n" + horizontal;
    }

    public void showWelcome() {
        System.out.println(addLine("Hello! I'm Ace\nWhat can I do for you?"));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void sendMessage(String message) {
        System.out.println(addLine(message));
    }

    public void showError(String message) {
        System.out.println(addLine(message));
    }
}
